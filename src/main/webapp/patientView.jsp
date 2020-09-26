<%-- 
    Document   : patientView
    Created on : Sep 23, 2020, 12:00:45 PM
    Author     : cesar31
--%>

<%@page import="com.hospital.model.Appointment"%>
<%@page import="com.hospital.model.Report"%>
<%@page import="com.hospital.model.Result"%>
<%@page import="java.util.List"%>
<%@page import="com.hospital.model.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    //Patient p = (Patient) session.getAttribute("profile");
    List<Result> results = (List<Result>) session.getAttribute("results");
    List<Report> reports = (List<Report>) session.getAttribute("reports");
    //List<Appointment> app = (List<Appointment>) session.getAttribute("app");
    //List<Appointment> appLab = (List<Appointment>) session.getAttribute("appLab");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css.html"%>
        <title>Hospital Pasteur</title>
    </head>
    <body>
        <!--Nab Bar-->
        <nav class="navbar navbar-expand-lg navbar-light sticky-top">
            <div class="container">
                <a class="navbar-brand" href="#">Inicio</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="#specialties">Link1 <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#exams">Link2</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Pricing</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Cuenta
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <a class="dropdown-item" href="#">Action</a>
                                <a class="dropdown-item" href="#">Another action</a>
                                <a class="dropdown-item" href="MainController?action=singOff">Cerrar Sesion</a>
                            </div>
                        </li>
                    </ul>
                </div>           
            </div>
        </nav>

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
                            <th scope="col">#</th>
                            <th scope="col">First</th>
                            <th scope="col">Last</th>
                            <th scope="col">Handle</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${app.size() > 0 && appLab.size() > 0}">

                            </c:when>
                            <c:otherwise>
                                <c:forEach var="a" items="${app}">
                                    <tr>
                                        <th scope="row">${a.date}</th>
                                        <td>${a.time}</td>
                                        <td>Otto</td>
                                        <td>@mdo</td>
                                    </tr>
                                </c:forEach>
                                <c:forEach var="appL" items="${appLab}">
                                    <tr>
                                        <th scope="row">${appL.date}</th>
                                        <td>Mark</td>
                                        <td>Otto</td>
                                        <td>@mdo</td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
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
                            <th scope="col">Consulta/Examen</th>
                            <th scope="col">Hora</th>
                            <th scope="col">Fecha</th>
                            <th scope="col">Ver m&aacutes</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${results.size() > 0 && reports.size() > 0}">
                                <%int i = 0, j = 0;
                                    while (reports.size() != i && results.size() != j) {
                                        if (reports.get(i).getDate().before(results.get(j).getDate())) {
                                %>
                                <tr>
                                    <th scope="row"><span class="badge badge-danger">Informe</span></th>
                                    <td>Informe: <%=reports.get(i).getDate()%></td>
                                    <td><%=reports.get(i).getTime()%></td>
                                    <td><%=reports.get(i).getDate()%></td>
                                    <td><a href="#" class="btn btn-outline-info">Ver más</a></td>
                                </tr>
                                <%
                                    i++;
                                    if (i == reports.size()) {
                                        while (results.size() != j) {
                                %>
                                <tr>
                                    <th scope="row"><span class="badge badge-info">Resultado</span></th>
                                    <td>Resultado: <%=results.get(j).getDate()%></td>
                                    <td><%=results.get(j).getTime()%></td>
                                    <td><%=results.get(j).getDate()%></td>
                                    <td><a href="#" class="btn btn-outline-info">Ver más</a></td>
                                </tr>
                                <%
                                            j++;
                                        }
                                    }
                                } else {
                                %>
                                <tr>
                                    <th scope="row"><span class="badge badge-info">Resultado</span></th>
                                    <td>Resultado: <%=results.get(j).getDate()%></td>
                                    <td>Otto</td>
                                    <td></td>
                                    <td><a href="#" class="btn btn-outline-info">Ver más</a></td>
                                </tr>
                                <%
                                    j++;
                                    if (j == results.size()) {
                                        while (reports.size() != i) {
                                %>
                                <tr>
                                    <th scope="row"><span class="badge badge-danger">Informe</span></th>
                                    <td><%=reports.get(i).getDate()%></td>
                                    <td>Otto</td>
                                    <td></td>
                                    <td><a href="#" class="btn btn-outline-info">Ver más</a></td>
                                </tr>
                                <%
                                                    i++;
                                                }
                                            }
                                        }
                                    }
                                %>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="rep" items="${reports}">
                                    <tr>
                                        <th scope="row"><span class="badge badge-danger">Informe</span></th>
                                        <td>Informe: ${rep.date}</td>
                                        <td>Otto</td>
                                        <td></td>
                                        <td><a href="#" class="btn btn-outline-info">Ver más</a></td>
                                    </tr>
                                </c:forEach>
                                <c:forEach var="res" items="${results}">
                                    <tr>
                                        <th scope="row"><span class="badge badge-info">Resultado</span></th>
                                        <td>Resultado: ${res.date}</td>
                                        <td>Otto</td>
                                        <td></td>
                                        <td><a href="#" class="btn btn-outline-info">Ver más</a></td>
                                    </tr>
                                </c:forEach>   
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </section>
        <%@include file="js.html"%>
    </body>
</html>
