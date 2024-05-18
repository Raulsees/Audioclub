<%@ page import="com.svalero.audioclub.dao.Database" %>
<%@ page import="com.svalero.audioclub.dao.DiscoDao" %>
<%@ page import="com.svalero.audioclub.domain.Disco" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="includes/header.jsp"%>

<main>
    <section class="py-5 text-center container">
        <h1>Ver Disco</h1>
    </section>
    <%
        // TODO Validar si viene el campo id
        int id_disco = Integer.parseInt(request.getParameter("id_disco"));

        Database.connect();
        Disco disco = Database.jdbi.withExtension(DiscoDao.class, dao -> dao.getDisco(id_disco));
    %>
    <div class="container">
        <div class="card text-center">
            <div class="card-header">
                Disco
            </div>
            <div class="card-body">
                <h5 class="card-title"><%= disco.getNombre() %></h5>
                <p class="card-text"><%= disco.getGenero() %></p>
                <a href="#" class="btn btn-primary">Alquilar</a>
            </div>

        </div>
    </div>
</main>

<%@include file="includes/footer.jsp"%>
