

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css.html" %>
        <title>Reservar Cita</title>
    </head>
    <body>

        <div class="container">
            <div class="row my-4">
                <div class="col text-center">
                    <h1 class="text-success">Nueva Cita</h1>
                </div>
            </div>
        </div>

        <div class="jumbotron jumbotron-fluid">
            <div class="container">
                <h3 class="display-4 my-0">Dr. ${doctor.name}</h3>
                <p class="lead my-0">Horario: ${doctor.startTime} - ${doctor.endTime}</p>
                <p class="lead my-0">Email: ${doctor.email}</p>
                <c:forEach var="s" items="${doctor.specialties}">
                    <p class="lead my-0"><span class="badge badge-info">${s.degree}</span></p>
                    </c:forEach>
            </div>
        </div>

        <section id="appointments-doctor">
            <div class="container my-4">
                <div class="row">
                    <div class="col text-center">
                        <h2 class="my-0">Citas</h2>
                        <h1 class="my-0">${date}</h1>
                    </div>
                </div>

                <div class="row my-4">
                    <div class="form">
                        <form>
                            <div class="form-group row">
                                <label for="date" class="col col-form-label">Fecha</label>
                                <div class="col">
                                    <input type="date" name="date" value="${date}" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="specialty" class="col col-form-label">Especialidad</label>
                                <div class="col">
                                    <select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="specialty" required>
                                        <option value="" selected>Choose...</option>
                                        <c:forEach var="s" items="${doctor.specialties}">
                                            <option value="${s.specialtyId}">${s.degree}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <c:forEach var="a" items="${appointments}">
                                <div class="form-group row">
                                    <p>${a.time}</p>
                                </div>
                            </c:forEach>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="js.html" %>
    </body>
</html>
