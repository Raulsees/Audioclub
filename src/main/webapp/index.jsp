<%@ page import="com.svalero.audioclub.dao.DiscoDao" %>
<%@ page import="com.svalero.audioclub.dao.Database" %>
<%@ page import="com.svalero.audioclub.domain.Disco" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="includes/header.jsp"%>

<main>
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light">DISCOTECA</h1>
                <p class="lead text-body-secondary">Gestor de discos</p>
                <p>
                    <a href="edit-disco.jsp" class="btn btn-primary my-2">Registrar nuevo disco</a>
                    <a href="#" class="btn btn-secondary my-2">Ver calendario</a>
                </p>
            </div>
        </div>
    </section>

    <div class="album py-5 bg-body-tertiary">
        <div class="container">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <%
                    Database.connect();
                    List<Disco> discos = Database.jdbi.withExtension(DiscoDao.class, dao -> dao.getAllDiscos());
                    for (Disco disco : discos) {

                %>
                <div class="col">
                    <div class="card shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg"
                             role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false">
                            <title>Placeholder</title>
                            <rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
                        <div class="card-body">
                            <p class="card-text"><strong><%= disco.getNombre() %></strong></p>
                            <p class="card-text"><%= disco.getGenero() %></p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <a href="view-disco.jsp" type="button" class="btn btn-sm btn-outline-primary">Ver</a>
                                    <a href="#" type="button" class="btn btn-sm btn-outline-primary">Editar</a>
                                    <a href="remove-disco?id=<%= disco.getId_disco() %>" type="button" class="btn btn-sm btn-outline-danger">Eliminar</a>
                                </div>
                                <small class="text-body-secondary"><%= disco.getAno() %> €</small>
                            </div>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
        </div>
    </div>
</main>

<%@include file="includes/footer.jsp"%>