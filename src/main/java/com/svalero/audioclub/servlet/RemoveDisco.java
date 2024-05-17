package com.svalero.audioclub.servlet;

import com.svalero.audioclub.dao.Database;
import com.svalero.audioclub.dao.DiscoDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/remove-disco")
public class RemoveDisco extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_disco = Integer.parseInt(request.getParameter("id_disco"));

        try {
            Database.connect();
            int affectedRows = Database.jdbi.withExtension(DiscoDao.class,
                    dao -> dao.removeDisco(id_disco));
            response.sendRedirect("index.jsp");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
