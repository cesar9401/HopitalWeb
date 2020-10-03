
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
                            <form action="DoctorController" method="post">
                                <div class="form-row my-0">
                                    <div class="col-12 col-md-2 my-2">
                                        <select id="inputType" class="form-control" name="kind" required>
                                            <option value="1" selected>Nombre</option>
                                            <option value="2">Especialidad</option>
                                            <option value="3">Horario(Rango)</option>
                                            <option value="4">Rango de Inicio de Trabajo</option>
                                        </select>
                                    </div>
                                    <div class="col-12 col-md-6 my-2" id="for-name">
                                        <input type="text" class="form-control" name="name" id="name" placeholder="Ingresa un nombre" required>
                                    </div>
                                    <div class="col-12 col-md-6 my-2" id="for-specialty" hidden>
                                        <select id="specialty" class="form-control" name="specialty">
                                            <option value="" selected>Choose...</option>
                                        <c:forEach var="s" items="${specialty}">
                                            <option value="${s.specialtyId}" selected>${s.degree}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="col-6 col-md-3 my-2" id="for-time" hidden>
                                    <input type="time" class="form-control" name="time1" id="time1">
                                </div>
                                <div class="col-6 col-md-3 my-2" id="for-time2" hidden>
                                    <input type="time" class="form-control" name="time2" id="time2">
                                </div>
                                <div class="col-12 col-md-3 my-2" id="for-date1" hidden>
                                    <input type="date" class="form-control" name="date1" id="date1">
                                </div>
                                <div class="col-12 col-md-3 my-2" id="for-date2" hidden>
                                    <input type="date" class="form-control" name="date2" id="date2">
                                </div>
                                <div class="col-12 col-md-2 my-2">
                                    <button type="submit" class="btn btn-info btn-block" name="action" value="searchDoctors">Buscar</button>
                                </div>
                                <div class="col-12 col-md-2 my-2">
                                    <a href="AdminController?action=newDoctor" class="btn btn-outline-info btn-block">Agregar</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>

        <!--Doctores-->
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
                                        <a href="AdminController?action=${d.doctorId}" class="btn btn-outline-dark">Editar</a>
                                    </li>

                                </ul>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>

        <!--Modal para informar sobre actualizacion de datos o creacion de nuevos doctores doctor-->
        <div class="modal fade" id="modal-info" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Información</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p id="modal-info-doctors">Se ha actualizado correctamente al doctor ${updateDoctor}</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="js.html" %>
        <script type="text/javascript">
            window.onload = function () {
                var list = document.getElementById('inputType');
                var forName = document.getElementById('for-name');
                var name = document.getElementById('name');
                var forSpecialty = document.getElementById('for-specialty');
                var specialty = document.getElementById('specialty');
                var forDate1 = document.getElementById('for-date1')
                var forDate2 = document.getElementById('for-date2')
                var date1 = document.getElementById('date1');
                var date2 = document.getElementById('date2');
                var divTime = document.getElementById('for-time');
                var time1 = document.getElementById('time1');
                var divTime2 = document.getElementById('for-time2');
                var time2 = document.getElementById('time2');


                list.addEventListener('change', () => {
                    forName.hidden = true;
                    forSpecialty.hidden = true;
                    forDate1.hidden = true;
                    forDate2.hidden = true;
                    //Tiempos
                    divTime.hidden = true;
                    divTime2.hidden = true;

                    //required
                    name.required = false;
                    specialty.required = false;
                    time1.required = false;
                    time2.required = false;
                    date1.required = false;
                    date2.required = false;

                    if (list.value === "1") {
                        forName.hidden = false;
                        name.required = true;
                    } else if (list.value === "2") {
                        forSpecialty.hidden = false;
                        specialty.required = true;
                    } else if (list.value === "3") {
                        divTime.hidden = false;
                        divTime2.hidden = false;
                        time1.required = true;
                        time1.required = true;
                    } else if (list.value === "4") {
                        forDate1.hidden = false;
                        forDate2.hidden = false;
                        date1.required = true;
                        date2.required = true;
                    }
                });
            };
        </script>
        <c:if test="${updateDoctor != null}">
            <script>
                $(document).ready(function () {
                    $('#modal-info').modal('show');
                });
            </script>
        </c:if>

        <c:if test="${nuevoDoc != null}">
            <script>
                var p = document.getElementById('modal-info-doctors');
                p.textContent = "Se ha ingresado correctamente al medico ${nuevoDoc}";
                $(document).ready(function () {
                    $('#modal-info').modal('show');
                });
            </script>
        </c:if>
    </body>
</html>
