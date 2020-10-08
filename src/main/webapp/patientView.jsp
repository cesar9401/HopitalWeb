<%-- 
    Document   : patientView
    Created on : Sep 23, 2020, 12:00:45 PM
    Author     : cesar31
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css.html"%>
        <title>Hospital Pasteur</title>
    </head>
    <body>

        <jsp:include page="WEB-INF/navPatient.jsp"></jsp:include>

            <!--Info profile-->
            <div class="jumbotron pt-4 pb-4">
                <div class="container text-center">
                    <h1 class="display-4">${profile.name}</h1>
                <p class="lead"><span class="font-weight-bold">Fecha de Nacimiento: </span>${profile.birth}</p>
                <p class="lead"><span class="font-weight-bold">Peso: </span>${profile.weight} Kg</p>
                <p class="lead"><span class="font-weight-bold">Tipo de Sangre: </span><span class="badge badge-danger">${profile.blood}</span></p>
                <p class="lead"><span class="font-weight-bold">Telefono: </span>${profile.phone}</p>
                <hr class="my-4">
                <p>HOSPITAL PASTEUR - Ciudad de Quetzaltenango, Guatemala</p>
                <a class="btn btn-outline-success" href="#" role="button">Actualizar Datos</a>
            </div>
        </div>

        <!--Mis citas-->
        <section id="mis-citas" class="mt-2 mb-2 pt-2 pb-2">
            <div class="container">
                <div class="row text-center">
                    <div class="col">
                        <h1>Mis citas</h1>
                    </div>
                </div>
                <table class="table sortable">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">Tipo</th>
                            <th scope="col">Doctor</th>
                            <th scope="col">Especialidad/Examen</th>
                            <th scope="col">Hora</th>
                            <th scope="col" id="aFecha">Fecha</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="a" items="${app}">
                            <tr>
                                <th scope="row"><span class="badge badge-primary">Cita Médica</span></th>
                                <td>Dr. ${a.doctorName}</td>
                                <td><span class="badge badge-success">${a.degree}</span></td>
                                <td>${a.time}</td>
                                <td>${a.date}</td>
                            </tr>
                        </c:forEach>
                        <c:forEach var="appL" items="${appLab}">
                            <tr>
                                <th scope="row"><span class="badge badge-secondary">Cita Laboratorio</span></th>
                                <td><span class="font-weight-bold">Solicita: </span>Dr. ${appL.doctorName}</td>
                                <td><span class="badge badge-warning">${appL.examName}</span></td>
                                <td>${appL.time}</td>
                                <td>${appL.date}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>

        <!--Historial Medico-->
        <section id="historial" class="mt-2 mb-2 pt-2 pb-2">
            <div class="container">
                <div class="row text-center mb-4">
                    <div class="col">
                        <h2>Mi Historial Medico</h2>
                    </div>
                </div>
                <table class="table sortable">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">Tipo</th>
                            <th scope="col">Responsable</th>
                            <th scope="col">Consulta/Examen</th>
                            <th scope="col">Hora</th>
                            <th scope="col" id="hFecha">Fecha</th>
                            <th scope="col">Ver m&aacutes</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="rep" items="${reports}">
                            <tr>
                                <th scope="row"><span class="badge badge-danger">Informe</span></th>
                                <td>Dr. ${rep.doctorName}</td>
                                <td><span class="badge badge-warning">${rep.degree}</span></td>
                                <td>${rep.time}</td>
                                <td>${rep.date}</td>
                                <td><a href="#" class="btn btn-outline-info">Ver más</a></td>
                            </tr>
                        </c:forEach>
                        <c:forEach var="res" items="${results}">
                            <tr>
                                <th scope="row"><span class="badge badge-info">Resultado</span></th>
                                <td>Lab. ${res.labWorkerName}</td>
                                <td><span class="badge badge-info">${res.examName}</span></td>
                                <td>${res.time}</td>
                                <td>${res.date}</td>
                                <td><a href="#" class="btn btn-outline-info">Ver más</a></td>
                            </tr>
                        </c:forEach>   
                    </tbody>
                </table>
            </div>
        </section>

        <!-- Modal para confirmar registro en el sistema-->
        <div class="modal fade" id="modal-patient" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Cita Agendada</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p id="info-modal-patient"></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="js.html"%>
        <script src="js/Sorttable.js"></script>
        <c:if test="${success != null}">
            <script>
                var p = document.getElementById('info-modal-patient');
                p.textContent = "Bienvenido al Hospital Pasteur ${success}";
                $(document).ready(function () {
                    $('#modal-patient').modal('show');
                });
            </script>
        </c:if>
    </body>
</html>
