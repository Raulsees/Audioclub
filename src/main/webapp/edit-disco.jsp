<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="includes/header.jsp"%>

<script type="text/javascript">
    $(document).ready(function() {
        $("form").on("submit", function(event) {
            event.preventDefault();
            var formValue = $(this).serialize();
            $.post("edit-disco", formValue, function(data) {
                $("#result").html(data);
            });
        });
    });
</script>

<main>
    <section class="py-5 container">
        <h1>Registrar nuevo Disco</h1>
        <form class="row g-3 needs-validation" novalidate>
            <div class="mb-3">
                <label for="name" class="form-label">Nombre</label>
                <input type="text" name="nombre" class="form-control" id="name" placeholder="Queen">
            </div>

            <div class="mb-3">
                <label for="description" class="form-label">Genero</label>
                <textarea rows="4" name="genero" cols="50" class="form-control" id="description" placeholder="PopRock">
                </textarea>
            </div>

            <div class="col-md-4">
                <label for="date" class="form-label">Fecha</label>
                <input type="date" name="date" class="form-control" id="date" placeholder="dd/mm/yyyy">
            </div>

            <div class="col-md-4">
                <label for="price" class="form-label">Año</label>
                <input type="text" name="ano" class="form-control" id="price" placeholder="15,00">
            </div>

            <div class="col-md-4">
                <label for="picture" class="form-label">Foto</label>
                <input type="file" name="picture" class="form-control" id="picture">
            </div>
            <div class="col-12">
                <button class="btn btn-primary" type="submit">Registrar</button>
            </div>
        </form>
        <br/>
        <div id="result"></div>
    </section>
</main>

<%@include file="includes/footer.jsp"%>