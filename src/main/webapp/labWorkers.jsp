
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css.html" %>
        <title>Administrador - ${profile.name}</title>
    </head>
    <body>
        <!--NavBar-->
        <jsp:include page="WEB-INF/navAdmin.jsp"></jsp:include>

            <section id="formSearch-lab">
                <div class="container">
                    <div class="row">
                        <div class="col text-center my-4">
                            <h1 class="text-primary display-4">Laboratoristas</h1>
                        </div>
                    </div>

                    <div class="row my-4">
                        <div class="col">
                            <!--Formulario Aqui-->
                            <form action="LabController" method="post">
                                <div class="form-row my-0">
                                    <div class="col-12 col-md-2 my-2">
                                        <select id="inputType" class="form-control" name="kind" required>
                                            <option value="1" selected>Nombre</option>
                                            <option value="2">Examen</option>
                                            <option value="3">Dias de Trabajo</option>
                                        </select>
                                    </div>
                                    <div class="col-12 col-md-6 my-2" id="for-name">
                                        <input type="text" class="form-control" name="name" id="name" placeholder="Ingresa un nombre" required>
                                    </div>
                                    <div class="col-12 col-md-6 my-2" id="for-exams" hidden>
                                        <select id="exams" class="form-control" name="exams">
                                            <option value="" selected>Choose...</option>
                                        <c:forEach var="e" items="${exams}">
                                            <option value="${e.examId}">${e.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-12 col-md-6 my-2" id="for-days" hidden>
                                    <select id="days" class="form-control" name="days">
                                        <option value="" selected>Choose...</option>
                                        <c:forEach var="d" items="${days}">
                                            <option value="${d.dayId}">${d.day}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-12 col-md-2 my-2">
                                    <button type="submit" class="btn btn-info btn-block" name="action" value="searchLab">Buscar</button>
                                </div>
                                <div class="col-12 col-md-2 my-2">
                                    <a href="LabController?action=newLabWorker" class="btn btn-outline-info btn-block">Agregar</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
        </section>

        <!--Laboratoristas-->
        <section id="labWorkers">
            <div class="container">
                <div class="row my-4">
                    <c:forEach var="l" items="${labWorkers}">
                        <div class="col-12 col-md-6 col-lg-4 my-2">

                            <div class="card">
                                <div class="card-header text-center">
                                    <h1 class="text-info">${l.name}</h1>
                                    <hr>
                                    <c:forEach var="d" items="${l.days}">
                                        <span class="badge badge-pill badge-success">
                                            ${d.day}
                                        </span>
                                    </c:forEach>
                                    <hr>
                                    <p class="lead my-0">Examen: <span class="badge badge-pill badge-warning">${l.exam.name}</span></p>
                                </div>
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item py-2"><span class="font-weight-bold">Registro de Salud: </span>${l.registry}</li>
                                    <li class="list-group-item py-2"><span class="font-weight-bold">DPI: </span>${l.dpi}</li>
                                    <li class="list-group-item py-2"><span class="font-weight-bold">Telefono: </span>${l.phone}</li>
                                    <li class="list-group-item py-2"><span class="font-weight-bold">Email: </span>${l.email}</li>
                                    <li class="list-group-item py-2"><span class="font-weight-bold">Fecha de Inicio: </span>${l.startDate}</li>
                                    <li class="list-group-item text-center">
                                        <a href="LabController?action=${l.labWorkerId}" class="btn btn-outline-dark">Editar</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
        
        <%@include file="js.html" %>
        <script type="text/javascript">
            window.onload = function () {
                var list = document.getElementById('inputType');
                list.addEventListener('change', () => {
                    $('#for-name').prop('hidden', true);
                    $('#name').prop('required', false);
                    $('#for-exams').prop('hidden', true);
                    $('#exams').prop('required', false);
                    $('#for-days').prop('hidden', true);
                    $('#days').prop('required', false);
                    if (list.value === "1") {
                        $('#for-name').prop('hidden', false);
                        $('#name').prop('required', true);
                    } else if (list.value === "2") {
                        $('#for-exams').prop('hidden', false);
                        $('#exams').prop('required', true);
                    } else if (list.value === "3") {
                        $('#for-days').prop('hidden', false);
                        $('#days').prop('required', true);
                    }
                });
            };
        </script>
    </body>
</html>
