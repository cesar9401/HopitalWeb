<%-- 
    Document   : doctorView
    Created on : Sep 29, 2020, 1:22:26 AM
    Author     : cesar31
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css.html" %>
        <title>Dr. ${profile.name}</title>
    </head>
    <body>

        <!--Nab Bar-->
        <nav class="navbar navbar-expand-lg navbar-light sticky-top">
            <div class="container">
                <a class="navbar-brand" href="#">Dr. ${profile.name}</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="#">Link1<span class="sr-only">(current)</span></a>
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

        <!--Datos doctor-->
        <div class="jumbotron jumbotron-fluid text-center">
            <div class="container">
                <h3 class="display-4 my-0">Dr. ${profile.name}</h3>
                <p class="lead my-0">Horario: ${profile.startTime} - ${profile.endTime}</p>
                <p class="lead my-0">Email: ${profile.email}</p>
                <c:forEach var="s" items="${profile.specialties}">
                    <p class="lead my-0"><span class="badge badge-info">${s.degree}</span> <span class="badge badge-light">Q${s.priceConsultation}</span></p>
                </c:forEach>
                <hr class="my-4">
                <p class="lead my-0">HOSPITAL PASTEUR - Ciudad de Quetzaltenango, Guatemala</p>
            </div>
        </div>    

        <!--Citas del dia-->
        <section id="citasHoy">
            <div class="contanier">
                <div class="row">
                    <div class="col text-center">
                        <h1 class="display-4    text-info">Citas del D&iacute;a</h1>
                    </div>
                </div>
            </div>
        </section>


        <%@include file="js.html" %>
    </body>
</html>
