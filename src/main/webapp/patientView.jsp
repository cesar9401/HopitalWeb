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
                    <p class="lead"><span class="font-weight-bold">Tipo de Sangre: </span>${profile.blood}</p>
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
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">Tipo</th>
                                <th scope="col">Doctor</th>
                                <th scope="col">Especialidad/Examen</th>
                                <th scope="col">Hora</th>
                                <th scope="col">Fecha</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="a" items="${app}">
                                <tr>
                                    <th scope="row"><span class="badge badge-primary">Cita Médica</span></th>
                                    <td>${a.doctorName}</td>
                                    <td><span class="badge badge-warning">${a.degree}</span></td>
                                    <td>${a.time}</td>
                                    <td>${a.date}</td>
                                </tr>
                            </c:forEach>
                            <c:forEach var="appL" items="${appLab}">
                                <tr>
                                    <th scope="row"><span class="badge badge-secondary">Cita Laboratorio</span></th>
                                    <th>${appL.doctorId}</th>
                                    <td>${appL.examId}</td>
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
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">Tipo</th>
                                <th scope="col">Doctor</th>
                                <th scope="col">Consulta/Examen</th>
                                <th scope="col">Hora</th>
                                <th scope="col">Fecha</th>
                                <th scope="col">Ver m&aacutes</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="rep" items="${reports}">
                                <tr>
                                    <th scope="row"><span class="badge badge-danger">Informe</span></th>
                                    <td>${rep.doctorId}</td>
                                    <td>Especialidad</td>
                                    <td>${rep.time}</td>
                                    <td>${rep.date}</td>
                                    <td><a href="#" class="btn btn-outline-info">Ver más</a></td>
                                </tr>
                            </c:forEach>
                            <c:forEach var="res" items="${results}">
                                <tr>
                                    <th scope="row"><span class="badge badge-info">Resultado</span></th>
                                    <td>${res.labWorkerId}</td>
                                    <td>${res.examId}</td>
                                    <td>${res.time}</td>
                                    <td>${res.date}</td>
                                    <td><a href="#" class="btn btn-outline-info">Ver más</a></td>
                                </tr>
                            </c:forEach>   
                        </tbody>
                    </table>
                </div>
            </section>
            <%@include file="js.html"%>
        </body>
    </html>
