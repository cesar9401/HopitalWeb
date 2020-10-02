
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

            <section id="formSearch">
                <div class="container">
                    <div class="row">
                        <div class="col text-center my-4">
                            <h1 class="text-primary display-4">Doctores</h1>
                        </div>
                    </div>

                    <div class="row my-4">
                        <div class="col">
                            <!--Formulario Aqui-->
                            <form action="#" method="post">
                                <div class="form-row my-0">
                                    <div class="col-12 col-md-2 my-2">
                                        <select id="inputType" class="form-control" name="kind" required>
                                            <option value="1" selected>Nombre</option>
                                            <option value="2">Especialidad</option>
                                            <option value="3">Rango de Horas de Trabajo</option>
                                            <option value="4">Rango de Inicio de Trabajo</option>
                                        </select>
                                    </div>
                                    <div class="col-12 col-md-6 my-2" id="for-name">
                                        <input type="text" class="form-control" name="name" id="name" placeholder="Ingresa un nombre">
                                    </div>
                                    <div class="col-12 col-md-6 my-2" id="for-specialty" hidden>
                                        <select id="inputType" class="form-control" name="specialty" required>
                                            <option value="" selected>Choose...</option>
                                        <c:forEach var="s" items="${specialty}">
                                            <option value="${s.specialtyId}" selected>${s.degree}</option>
                                        </c:forEach>
                                        </select>
                                    </div>

                                <div class="col-12 col-md-2 my-2">
                                    <button type="submit" class="btn btn-info btn-block" name="action" value="searchDoctors">Buscar</button>
                                </div>
                                <div class="col-12 col-md-2 my-2">
                                    <a href="#" class="btn btn-outline-info btn-block">Agregar</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>

        <section id="doctors-Admin">
            <div class="container">
                <div class="row my-4">
                    <c:forEach var="d" items="${doctors}">
                        <div class="col-12 col-md-6 col-lg-4 my-2">
                            <div class="card">
                                <div class="card-header text-center">
                                    <h1 class="text-success">${d.name}</h1>
                                    <hr>
                                    <c:forEach var="s" items="${d.specialties}">
                                        <span class="badge badge-pill badge-info">
                                            ${s.degree}
                                        </span>
                                    </c:forEach>
                                </div>
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item py-2"><span class="font-weight-bold">Colediado: </span>${d.collegiate}</li>
                                    <li class="list-group-item py-2"><span class="font-weight-bold">DPI: </span>${d.dpi}</li>
                                    <li class="list-group-item py-2"><span class="font-weight-bold">Telefono: </span>${d.phone}</li>
                                    <li class="list-group-item py-2"><span class="font-weight-bold">Email: </span>${d.email}</li>
                                    <li class="list-group-item py-2"><span class="font-weight-bold">Horario: </span>${d.startTime} - ${d.endTime}</li>
                                    <li class="list-group-item py-2"><span class="font-weight-bold">Fecha de Inicio: </span>${d.startDate}</li>
                                    <li class="list-group-item text-center">
                                        <a href="#" class="btn btn-outline-dark">Editar</a>
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
            window.onload = function() {
                var list = document.getElementById('inputType');
                var divName = document.getElementById('for-name');
                var divSpecialty = document.getElementById('for-specialty');

                list.addEventListener('change', () => {
                    if (list.value === "1") {
                        divName.hidden = false;
                        divSpecialty.hidden = true;
                    }
                    if (list.value === "2") {
                        divName.hidden = true;
                        divSpecialty.hidden = false;
                    }
                });
            };
        </script>
    </body>
</html>
    