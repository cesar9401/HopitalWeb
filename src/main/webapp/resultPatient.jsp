
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

            <section id="profile-patient">
                <div class="container">
                    <div class="row my-4">
                        <div class="col text-center">
                            <h1 class="text-secondary">Cita #${actualApp.appointmentId}</h1>
                    </div>
                </div>


                <div class="row">
                    <div class="col-12 col-md-4">
                        <div class="card">
                            <img src="resources/patientLab.jpg" class="card-img-top" alt="${patient.name}">
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
                            <h1 class="my-0">Examen de Laboratorio <span class="badge badge-success">${actualApp.examName}</span></h1>
                            <h2 class="my-0">Laboratorista. ${profile.name}</h2>
                            <p class="lead my-0"><span class="font-weight-bold">Fecha: </span>${actualApp.date}</p>
                            <p class="lead my-0"><span class="font-weight-bold">Hora: </span>${actualApp.time}</p>
                        </div>

                        <div id="formulario">
                            <form action="OperationsLab" method="post" enctype="multipart/form-data">
                                <div class="form-group text-center">
                                    <div class="col">
                                        <label for="input-result">Resultado </label>
                                        <input type="file" id="input-result" name="input-result" required>                                        
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="row">
                                        <div class="col">
                                            <label for="time">Hora</label>
                                            <input type="time" name="time" value="${actualApp.time}" required>  
                                        </div>
                                        <div class="col text-right">
                                            <label for="date">Fecha</label>
                                            <input type="date" name="date" value="${actualApp.date}" required>
                                        </div>    
                                    </div>
                                    <div class="row">
                                        <div class="col-4 offset-4 my-4">
                                            <input type="hidden" name="appointmentId" value="${actualApp.appointmentId}">
                                            <input type="hidden" name="patientId" value="${actualApp.patientId}">
                                            <input type="hidden" name="labWorkerId" value="${profile.labWorkerId}">
                                            <input type="hidden" name="examId" value="${actualApp.examId}">
                                            <button type="submit" name="action" value="newResult" class="btn btn-outline-success btn-block">Guardar</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>               

                </div>
        </section>

        <%@include file="js.html" %>
        <script type="text/javascript">
            <c:choose>
                <c:when test="${thisExam.report}">
            $('#input-result').prop('accept', 'application/pdf');
                </c:when>
                <c:otherwise>
            $('#input-result').prop('accept', 'image/jpeg,image/gif,image/png');
                </c:otherwise>
            </c:choose>
        </script>
    </body>
</html>
