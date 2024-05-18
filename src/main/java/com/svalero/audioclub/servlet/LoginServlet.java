package com.svalero.audioclub.servlet;


import com.svalero.audioclub.dao.ClienteDao;
import com.svalero.audioclub.dao.Database;
import com.svalero.audioclub.domain.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static com.svalero.audioclub.util.ErrorUtils.sendError;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String dni = request.getParameter("dni");
        String password = request.getParameter("password");

        try {
            Database.connect();
            Cliente cliente = Database.jdbi.withExtension(ClienteDao.class,
                    dao -> dao.getCliente(dni, password));
            if (cliente != null) {
                HttpSession session = request.getSession();
                session.setAttribute("dni", cliente.getDni());
                session.setAttribute("role", cliente.getRole());
                response.getWriter().print("ok");
            } else {
                sendError("El usuario no existe", response);
            }
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            sendError("Internal Server Error", response);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            sendError("Error conectando con la base de datos", response);
        }
    }

}
