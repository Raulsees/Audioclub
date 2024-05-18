package com.svalero.audioclub.servlet;

import com.svalero.audioclub.dao.Database;
import com.svalero.audioclub.dao.DiscoDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.http.HttpClient;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet("/edit-disco")
public class EditDisco extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try {
            if (hasValidationErrors(request, response))
                return;

            String nombre = request.getParameter("nombre");
            int ano = Integer.parseInt(request.getParameter("ano"));
            String genero = request.getParameter("genero");
            String picture = request.getParameter("picture");

            Database.connect();
            int affectedRows = Database.jdbi.withExtension(DiscoDao.class,
                    dao -> dao.addDisco(nombre, ano, genero, picture));
            sendMessage("Disco registrada correctamente", response);
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
