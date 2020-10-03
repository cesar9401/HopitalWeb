<%-- 
    Document   : labWorkerView
    Created on : Oct 3, 2020, 11:52:34 AM
    Author     : cesar31
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css.html" %>
        <title>Laboratorio - ${profile.name}</title>
    </head>
    <body>
        <!--NavBar-->
        <jsp:include page="WEB-INF/navLabWorker.jsp"></jsp:include>

            <!--Datos Laboratorista-->
            <div class="jumbotron text-center">
                <div class="container">
                    <h1 class="display-4">Laboratorista: ${profile.name}</h1>
                <p class="lead">
                    <span class="font-weight-bold">Dias de Trabajo: </span>
                    <c:forEach var="d" items="${profile.days}">
                        <span class="badge badge-info">${d.day} </span>
                    </c:forEach>
                </p>
                <p class="lead"><span class="font-weight-bold">Registro de Salud: </span>${profile.registry}</p>
                <hr class="my-2">
                <!--Datos Examen-->
                
                <hr class="my-4">
                <p class="lead my-0">HOSPITAL PASTEUR - Ciudad de Quetzaltenango, Guatemala</p>
            </div>
        </div>

        <%@include file="js.html" %>
    </body>
</html>
