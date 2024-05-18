package com.svalero.audioclub.servlet;

import com.svalero.audioclub.dao.Database;
import com.svalero.audioclub.dao.DiscoDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.UUID;

@WebServlet("/edit-disco")
public class EditDisco extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        HttpSession currentSession = request.getSession();
        if (currentSession.getAttribute("role") != null) {
            if (!currentSession.getAttribute("role").equals("admin")) {
                response.sendRedirect("/audioclub");
            }
        }

        try {
            if (hasValidationErrors(request, response))
                return;

            int id = 0;
            if (request.getParameter("id_cliente") != null) {
                id = Integer.parseInt(request.getParameter("id_cliente"));
            }

            String nombre = request.getParameter("nombre");
            int ano = Integer.parseInt(request.getParameter("ano"));
            String genero = request.getParameter("genero");
            Part picturePart = request.getPart("picture");

            String imagePath = request.getServletContext().getInitParameter("image-path");
            String filename = null;
            if (picturePart.getSize() == 0) {
                filename = "no-image.jpg";
            } else {
                filename = UUID.randomUUID() + ".jpg";
                InputStream fileStream = picturePart.getInputStream();
                Files.copy(fileStream, Path.of(imagePath + File.separator + filename));
            }

            Database.connect();
            final String finalFilename = filename;
            if (id == 0) {
                int affectedRows = Database.jdbi.withExtension(DiscoDao.class,
                        dao -> dao.addDisco(nombre, ano, genero, finalFilename));
                sendMessage("Disco registrado correctamente", response);
            } else {
                final int finalId = id;
                int affectedRows = Database.jdbi.withExtension(DiscoDao.class,
                        dao -> dao.updateDisco(nombre, ano, genero, finalFilename, finalId));
                sendMessage("Disco registrado correctamente", response);
            }
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            sendError("Internal Server Error", response);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            sendError("Error conectando con la base de datos", response);
        }
    }

    private boolean hasValidationErrors(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean hasErrors = false;

        if (request.getParameter("nombre").isBlank()) {
            sendError("El nombre es un campo obligatorio", response);
            hasErrors = true;
        }

        if (request.getParameter("genero").isBlank()) {
            sendError("El genero es un campo obligatorio", response);
            hasErrors = true;
        }

        return hasErrors;
    }

    private void sendError(String message, HttpServletResponse response) throws IOException {
        response.getWriter().println("<div class='alert alert-danger' role='alert'>" + message + "</div>");
    }

    private void sendMessage(String message, HttpServletResponse response) throws IOException {
        response.getWriter().println("<div class='alert alert-success' role='alert'>" + message + "</div>");
    }
}
