
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css.html" %>
        <title>New Appointment</title>
    </head>
    <body>

        <!--NavBar-->

        <!--Nueva cita-->
        <section id="nueva-cita" class="my-4 py-2">
            <div class="container">

                <div class="row text-center">
                    <div class="col">
                        <h2 class="text-primary">Agendar Cita Médica</h2>
                    </div>
                </div>

                <div class="row my-3" id="row-especialidad">
                    <div class="col">
                        <form action="MainController" method="post">
                            <div class="form-row align-items-center">
                                <div class="col-auto ml-2">
                                    <label for="specialties">Especialidad</label>
                                </div>
                                <div class="col-4 my-1">
                                    <select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="specialties" required>
                                        <option value="" selected>Choose...</option>
                                        <c:forEach var="s" items="${specialties}">
                                            <option value="${s.specialtyId}">${s.degree}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-auto ml-2">
                                    <label for="date">Fecha</label>
                                </div>
                                <div class="col my-1">
                                    <input type="date" name="date" required>
                                </div>
                                <div class="col my-1 ml-2">
                                    <button type="submit" name="action" value="d_specialties" class="btn btn-info">Buscar</button>  
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <div id="doctors" class="px-2 my-4">
                    <div class="row">
                        <div class="col text-center">
                            <h3 class="text-success">Doctores</h3>
                        </div>
                    </div>
                    <div class="row">
                        <c:forEach var="d" items="${doctors}">
                            <c:set var="str" value="doctor.jpg"></c:set>
                            <c:if test="${d.name.length() % 2 == 0 }">
                                <c:set var="str" value="doctor1.jpg"></c:set>
                            </c:if>
                            <div class="col-12 col-md-6 col-lg-4">
                                <div class="card my-2">
                                    <img src="resources/${str}" class="card-img-top" alt="${d.name}" title="${d.name}">
                                    <div class="card-body text-center">
                                        <h5 class="card-title my-0">Dr. ${d.name}</h5>
                                        <c:forEach var="s" items="${d.specialties}">
                                            <p class="card-text my-0"><span class="badge badge-dark">${s.degree}</span></p>
                                        </c:forEach>
                                    </div>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">Entrada: ${d.startTime}</li>
                                        <li class="list-group-item">Salida: ${d.endTime}</li>
                                        <li class="list-group-item">Contacto: ${d.email}</li>
                                    </ul>
                                    <div class="card-body text-center">
                                        <a href="MainController?action=${d.doctorId}" class="card-link">Agendar Cita</a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="js.html" %>
    </body>
</html>
