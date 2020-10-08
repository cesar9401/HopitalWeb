
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
        <jsp:include page="WEB-INF/navDoctor.jsp"></jsp:include>

            <section id="reports-doctor">
                <div class="container">
                    <div class="row">
                        <div class="col text-center">
                            <h1 class="text-info display-2 my-4">Reportes</h1>
                            <h1 class="text-info display-1 my-4">Dr. ${profile.name}</h1>
                    </div>

                </div>
            </div>

            <div class="accordion" id="accordionExample">
                <div class="container">
                    <div class="card">
                        <div class="card-header" id="headingOne">
                            <h2 class="mb-0">
                                <button class="btn btn-link btn-block text-left" id="report1" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    Reporte de citas agendadas en un intervelo de tiempo
                                </button>
                            </h2>
                        </div>

                        <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
                            <!--Reporte 1-->
                            <div class="card-body">
                                <div class="row">
                                    <div class="col">
                                        <form action="ReportOperation" method="post">
                                            <div class="form-row my-2">
                                                <div class="col-12 col-md-5 my-2" id="for-date1">
                                                    <input type="date" class="form-control" name="date1" id="date1" required>
                                                </div>
                                                <div class="col-12 col-md-5 my-2" id="for-date2">
                                                    <input type="date" class="form-control" name="date2" id="date2" required>
                                                </div>
                                                <div class="col-12 col-md-2 my-2">
                                                    <button type="submit" class="btn btn-info btn-block" name="action" value="doctor1">Buscar</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col">
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th scope="col">#</th>
                                                    <th scope="col">Paciente</th>
                                                    <th scope="col">Tipo</th>
                                                    <th scope="col">Estado</th>
                                                    <th scope="col">Hora</th>
                                                    <th scope="col">Fecha</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="a" items="${report1}">
                                                    <tr>
                                                        <th scope="row">${a.appointmentId}</th>
                                                        <td>${a.patientName}</td>
                                                        <td>${a.degree}</td>
                                                        <c:choose>
                                                            <c:when test="${a.status}">
                                                                <td><span class="badge badge-danger">Atendido</span></td>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <td><span class="badge badge-info">No Atendido</span></td>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <td>${a.time}</td>
                                                        <td>${a.date}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--Reporte 2-->
                    <div class="card">
                        <div class="card-header" id="headingTwo">
                            <h2 class="mb-0">
                                <button class="btn btn-link btn-block text-left collapsed" id="report2" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    Reporte de citas para el día en curso
                                </button>
                            </h2>
                        </div>

                        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col">
                                        <form action="ReportOperation" method="post">
                                            <div class="form-row my-2">
                                                <div class="col my-2" id="for-date3">
                                                    <input type="date" class="form-control" name="date3" id="date3" required>
                                                </div>
                                                <div class="col my-2">
                                                    <button type="submit" class="btn btn-info btn-block" name="action" value="doctor2">Buscar</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col">
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th scope="col">#</th>
                                                    <th scope="col">Paciente</th>
                                                    <th scope="col">Tipo</th>
                                                    <th scope="col">Estado</th>
                                                    <th scope="col">Hora</th>
                                                    <th scope="col">Fecha</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="a" items="${report2}">
                                                    <tr>
                                                        <th scope="row">${a.appointmentId}</th>
                                                        <td>${a.patientName}</td>
                                                        <td>${a.degree}</td>
                                                        <c:choose>
                                                            <c:when test="${a.status}">
                                                                <td><span class="badge badge-danger">Atendido</span></td>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <td><span class="badge badge-info">No Atendido</span></td>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <td>${a.time}</td>
                                                        <td>${a.date}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!--Reporte 3-->
                    <div class="card">
                        <div class="card-header" id="headingThree">
                            <h2 class="mb-0">
                                <button class="btn btn-link btn-block text-left collapsed" id="report3" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                    Los pacientes con mayor cantidad de informes médicos dentro de un intervalo de tiempo.
                                </button>
                            </h2>
                        </div>
                        <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col">
                                        <form action="ReportOperation" method="post">
                                            <div class="form-row my-2">
                                                <div class="col-12 col-md-5 my-2" id="for-date1">
                                                    <input type="date" class="form-control" name="date4" id="date4" required>
                                                </div>
                                                <div class="col-12 col-md-5 my-2" id="for-date2">
                                                    <input type="date" class="form-control" name="date5" id="date5" required>
                                                </div>
                                                <div class="col-12 col-md-2 my-2">
                                                    <button type="submit" class="btn btn-info btn-block" name="action" value="doctor3">Buscar</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col">
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th scope="col">id</th>
                                                    <th scope="col">Nombre</th>
                                                    <th scope="col">Genero</th>
                                                    <th scope="col">Fecha de Nacimiento</th>
                                                    <th scope="col">Telefono</th>
                                                    <th scope="col">Peso</th>
                                                    <th scope="col">Tipo de Sangre</th>
                                                    <th scope="col">Reportes</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="p" items="${report3}">
                                                    <tr>
                                                        <th scope="row">${p.patientId}</th>
                                                        <td>${p.name}</td>
                                                        <c:choose>
                                                            <c:when  test="${p.gender}">
                                                                <td><span class="badge badge-primary">Masculino</span></td>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <td><span class="badge badge-warning">Femenino</span></td>
                                                            </c:otherwise>
                                                        </c:choose>>
                                                        <td>${p.birth}</td>
                                                        <td>${p.phone}</td>
                                                        <td>${p.weight} Kg</td>
                                                        <td><span class="badge badge-danger">${p.blood}</span></td>
                                                        <td>${p.quantity}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>



        <%@include file="js.html" %>
        <script>
            window.onload = function () {
            <c:if test="${report1 != null}">
                $('#report1').click();
                $('#date1').val("${date1}");
                $('#date2').val("${date2}");
            </c:if>

            <c:if test="${report2 != null}">
                $('#report2').click();
                $('#date3').val("${date3}");
            </c:if>

            <c:if test="${report3 != null}">
                $('#report3').click();
                $('#date4').val("${date4}");
                $('#date5').val("${date5}");
            </c:if>
            };
        </script>
    </body>
</html>
