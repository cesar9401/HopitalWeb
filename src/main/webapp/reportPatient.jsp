<%-- 
    Document   : reportPatient
    Created on : Sep 29, 2020, 11:39:03 PM
    Author     : cesar31
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css.html" %>
        <title>Dr ${profile.name}</title>
    </head>
    <body>
        <!--NavBar-->
        <jsp:include page="WEB-INF/navDoctor.jsp"></jsp:include>

            <section id="profile-Patient">
                <div class="container">
                    <div class="row my-4">
                        <div class="col text-center">
                            <h1 class="text-secondary">Cita #${appointment.appointmentId}</h1>
                    </div>
                </div>

                <div class="row">
                    <div class="col-12 col-md-4">
                        <div class="card">
                            <img src="resources/patient.jpg" class="card-img-top" alt="...">
                            <div class="card-body">
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item"><span class="font-weight-bold">Nombre: </span>${patient.name}</li>
                                    <li class="list-group-item"><span class="font-weight-bold">Nacimiento: </span>${patient.birth}</li>
                                    <li class="list-group-item"><span class="font-weight-bold">Peso: </span>${patient.weight} Kg.</li>
                                    <li class="list-group-item"><span class="font-weight-bold">Sangre: </span><span class="badge badge-danger">${patient.blood}</span></li>
                                    <li class="list-group-item">
                                        <span class="font-weight-bold">G&eacute;nero: </span>
                                        <c:choose>
                                            <c:when test="${patient.gender}">
                                                <span class="badge badge-dark">Masculino</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge badge-light">Femenino</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="col-12 col-md-8">
                        <div class="jumbotron jumbotron-fluid p-4">
                            <h1 class="my-0">Cita Medica: <span class="badge badge-success">${appointment.degree}</span></h1>
                            <h2 class="my-0">Dr(a). ${profile.name}</h2>
                            <p class="lead my-0"><span class="font-weight-bold">Fecha: </span>${appointment.date}</p>
                            <p class="lead my-0"><span class="font-weight-bold">Hora: </span>${appointment.time}</p>
                        </div>

                        <div id="formulario">
                            <form action="DoctorController" method="post">
                                <div class="form-group">
                                    <label for="report">Reporte</label>
                                    <textarea class="form-control" name="report" rows="10" required></textarea>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col">
                                            <label for="time">Hora</label>
                                            <input type="time" name="time" value="${appointment.time}" required>  
                                        </div>
                                        <div class="col text-right">
                                            <label for="date">Fecha</label>
                                            <input type="date" name="date" value="${appointment.date}" required>
                                        </div>    
                                    </div>
                                    <div class="row">
                                        <div class="col-4 offset-4 my-4">
                                            <input type="hidden" name="appointmentId" value="${appointment.appointmentId}">
                                            <input type="hidden" name="patientId" value="${appointment.patientId}">
                                            <input type="hidden" name="doctorId" value="${appointment.doctorId}">
                                            <button type="submit" name="action" value="newReport" class="btn btn-outline-success btn-block">Guardar</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <%@include file="js.html" %>
    </body>
</html>
