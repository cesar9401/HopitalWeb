<%-- 
    Document   : adminView
    Created on : Sep 22, 2020, 7:57:12 PM
    Author     : cesar31
--%>

<%@page import="com.hospital.model.Exam"%>
<%@page import="com.hospital.model.Specialty"%>
<%@page import="com.hospital.model.Doctor"%>
<%@page import="java.util.List"%>
<%@page import="com.hospital.model.Administrator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String user = (String) session.getAttribute("user");
    Administrator a = (Administrator) session.getAttribute("profile");
    List<Specialty> specialties = (List<Specialty>) session.getAttribute("specialties");
    List<Exam> exams = (List<Exam>) session.getAttribute("exams");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css.html"%>
        <title>Admin - PASTEUR</title>
    </head>
    <body data-spy="scroll" data-target="#navbarNavDropdown" data-offset="57">

        <!--NavBar-->
        <nav class="navbar navbar-expand-lg navbar-light sticky-top">
            <div class="container">
                <a class="navbar-brand" href="#">Inicio</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="#specialties">Especialidades <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#exams">Examenes</a>
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
        <div class="jumbotron">
            <div class="container text-center">
                <h1 class="display-4">Administrador</h1>
                <p class="lead"><%=a.getAdminId()%></p>
                <p class="lead"><%=a.getName()%></p>
                <hr class="my-4">
                <p>HOSPITAL PASTEUR - Ciudad de Quetzaltenango, Guatemala</p>
                <a class="btn btn-primary btn-lg" href="#" role="button">Editar</a>
            </div>
        </div>

        <!--Specialties-->
        <section id="specialties" class="mt-4 mb-4">
            <div class="container">
                <div class="row">
                    <div class="col text-center">
                        <h1>Especialidades</h1>
                        <h2>Disponibles</h2>
                    </div>
                </div>
                <div class="row mt-2">
                    <%for (Specialty e : specialties) {%>
                    <%String str = "specialty_1";
                        if (e.getPriceConsultation() % 2 == 0) {
                            str = "specialty_2";
                        }
                    %>
                    <div class="col-md-6 col-lg-4 mb-4">
                        <div class="card">
                            <img src="resources/<%=str%>.jpg" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h4 class="card-title"><span class="font-weight-bold">Especialidad: </span><%=e.getDegree()%></h4>
                                <p class="card-text"><span class="font-weight-bold">Precio: </span>Q.<%=e.getPriceConsultation()%></p>
                                <a href="#" class="btn btn-primary">Editar</a>
                            </div>
                        </div>
                    </div>
                    <%}%>
                </div>
            </div>
        </section>

        <!--Exams-->
        <section id="exams" class="mt-4 mb-4">
            <div class="contanier">
                <div class="row">
                    <div class="col text-center">
                        <h1 class="display-3">Examenes</h1>
                    </div>
                </div>
                <div class="container">

                    <div class="row mt-2">
                        <%for (Exam e : exams) {%>
                        <div class="col-12 col-md-6">
                            <div class="jumbotron jumbotron-fluid p-4">
                                <h1><span class="font-weight-bold">Nombre: </span><%=e.getName()%></h1>
                                <p class="lead font-weight-bold mt-1 mb-0">Descripcion</p>
                                <p class="lead mt-0"><%=e.getDescription()%></p>
                                <p class="lead"><span class="font-weight-bold">Precio: </span>Q.<%=e.getPrice()%></p>
                            </div>
                        </div>
                        <%}%>
                    </div>
                </div>
            </div>
        </section>

        <%@include file="js.html"%>
    </body>
</html>
