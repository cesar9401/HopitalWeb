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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css.html"%>
        <title>Administrador - ${profile.name}</title>
    </head>
    <body data-spy="scroll" data-target="#navbarNavDropdown" data-offset="57">

        <!--NavBar-->
        <jsp:include page="WEB-INF/navAdmin.jsp"></jsp:include>

        <!--Info profile-->
        <div class="jumbotron">
            <div class="container text-center">
                <h1 class="display-4">Administrador</h1>
                <p class="lead">${profile.adminId}</p>
                <p class="lead">${profile.name}</p>
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
                        <h1 class="display-4 text-success">Especialidades</h1>
                    </div>
                </div>
                <div class="row mt-2">
                    <c:forEach var="e" items="${specialties}">
                        <c:set var="str" value="specialty_1"></c:set>
                        <c:if test="${e.priceConsultation % 2 == 0}">
                            <c:set var="str" value="specialty_2"></c:set>
                        </c:if>
                        <div class="col-md-6 col-lg-4 mb-4">
                            <div class="card">
                                <img src="resources/${str}.jpg" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h4 class="card-title"><span class="font-weight-bold">Especialidad: </span>${e.degree}</h4>
                                    <p class="card-text"><span class="font-weight-bold">Precio: </span>Q.${e.priceConsultation}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
        <hr/>

        <!--Exams-->
        <section id="exams" class="mt-4 mb-4">
            <div class="contanier">
                <div class="row">
                    <div class="col text-center">
                        <h1 class="display-3 text-info">Examenes</h1>
                    </div>
                </div>
                <div class="container">

                    <div class="row mt-2">
                        <c:forEach var="e" items="${exams}">
                            <div class="col-12 col-md-6">
                                <div class="jumbotron jumbotron-fluid p-4">
                                    <h1><span class="font-weight-bold">Nombre: </span>${e.name}</h1>
                                    <p class="lead font-weight-bold mt-1 mb-0">Descripcion</p>
                                    <p class="lead mt-0">${e.description}</p>
                                    <p class="lead"><span class="font-weight-bold">Precio: </span>Q.${e.price}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </section>
        <%@include file="js.html"%>
    </body>
</html>
