<%-- 
    Document   : patientView
    Created on : Sep 23, 2020, 12:00:45 PM
    Author     : cesar31
--%>

<%@page import="com.hospital.model.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Patient p = (Patient) session.getAttribute("profile");
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
                <h1 class="display-4"><%=p.getName()%></h1>
                <p class="lead"><span class="font-weight-bold">Fecha de Nacimiento: </span><%=p.getBirth()%></p>
                <p class="lead"><span class="font-weight-bold">Peso: </span><%=p.getWeight()%> Kg</p>
                <p class="lead"><span class="font-weight-bold">Tipo de Sangre: </span><%=p.getBlood()%></p>
                <p class="lead"><span class="font-weight-bold">Telefono: </span><%=p.getPhone()%></p>
                <hr class="my-4">
                <p>HOSPITAL PASTEUR - Ciudad de Quetzaltenango, Guatemala</p>
                <a class="btn btn-outline-success" href="#" role="button">Actualizar Datos</a>
            </div>
        </div>

        <!--HISTORIAL MEDICO-->
        <section id="historial" class="mt-2 mb-2 pt-2 pb-2">
            <div class="container">
                <div class="row text-center">
                    <div class="col">
                        <h2>Mi Historial Medico</h2>
                    </div>
                </div>
            </div>
        </section>

        <%@include file="js.html"%>
    </body>
</html>
