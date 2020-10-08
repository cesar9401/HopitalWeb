
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css.html" %>
        <title>Hospital Pasteur</title>
    </head>
    <body>
        <!--NavBar-->
        <jsp:include page="WEB-INF/navPatient.jsp"></jsp:include>

            <section id="reports-patient">

                <div class="container">
                    <div class="row">
                        <div class="col text-center">
                            <h1 class="text-info display-4">Reportes</h1>
                            <h1 class="text-info display-3">${profile.name}</h1>
                    </div>
                </div>
            </div>


            <div class="accordion" id="accordionExample">
                <div class="container">

                    <!--Reporte 1-->
                    <div class="card">
                        <div class="card-header" id="headingOne">
                            <h2 class="mb-0">
                                <button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    Últimos 5 exámenes de laboratorio realizados
                                </button>
                            </h2>
                        </div>

                        <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col">
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Id Resultado</th>
                                                    <th scope="col">Examen</th>
                                                    <th scope="col">Encargado</th>
                                                    <th scope="col">Hora</th>
                                                    <th scope="col">Fecha</th>
                                                    <th scope="col">Resultado</th>
                                                </tr>
                                            </thead>
                                            <tbody>                                                    
                                                <c:forEach var="r" items="${patient1}">
                                                    <tr>
                                                        <th scope="row">${r.resultId}</th>
                                                        <td><span class="badge badge-success">${r.examName}</span></td>
                                                        <td>Lab. ${r.labWorkerName}</td>
                                                        <td>${r.time}</td>
                                                        <td>${r.date}</td>
                                                        <td><a href="#" class="btn btn-danger btn-sm" target="_blank">Resultado</a></td>
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
                                <button class="btn btn-link btn-block text-left collapsed" id="patient2" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    Exámenes realizados de un tipo en específico dentro de un intervalo de tiempo.
                                </button>
                            </h2>
                        </div>
                        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col">
                                        <form action="ReportOperation" method="post">
                                            <div class="form-row my-2">
                                                <div class="col-12 col-md-4 my-2" id="for-exam">
                                                    <select name="exam" class="form-control" id="exam" required>
                                                        <option value="" selected>Choose...</option>
                                                        <c:forEach var="e" items="${examsPatient}">
                                                            <option value="${e.examId}">${e.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="col-12 col-md-3 my-2" id="for-date1">
                                                    <input type="date" class="form-control" name="date1" id="date1" required>
                                                </div>
                                                <div class="col-12 col-md-3 my-2" id="for-date2">
                                                    <input type="date" class="form-control" name="date2" id="date2" required>
                                                </div>
                                                <div class="col-12 col-md-2 my-2">
                                                    <button type="submit" class="btn btn-info btn-block" name="action" value="patient2">Buscar</button>
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
                                                    <th scope="col">Id Resultado</th>
                                                    <th scope="col">Examen</th>
                                                    <th scope="col">Encargado</th>
                                                    <th scope="col">Hora</th>
                                                    <th scope="col">Fecha</th>
                                                    <th scope="col">Resultado</th>
                                                </tr>
                                            </thead>
                                            <tbody>                                                    
                                                <c:forEach var="r" items="${patient2}">
                                                    <tr>
                                                        <th scope="row">${r.resultId}</th>
                                                        <td><span class="badge badge-success">${r.examName}</span></td>
                                                        <td>Lab. ${r.labWorkerName}</td>
                                                        <td>${r.time}</td>
                                                        <td>${r.date}</td>
                                                        <td><a href="#" class="btn btn-danger btn-sm" target="_blank">Resultado</a></td>
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
                                <button class="btn btn-link btn-block text-left collapsed" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                    Últimas 5 consultas realizadas
                                </button>
                            </h2>
                        </div>
                        <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col">
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Id Cita</th>
                                                    <th scope="col">Doctor</th>
                                                    <th scope="col">Tipo</th>
                                                    <th scope="col">Hora</th>
                                                    <th scope="col">Fecha</th>
                                                    <th scope="col">Informe</th>
                                                </tr>
                                            </thead>
                                            <tbody>                                                    
                                                <c:forEach var="r" items="${patient3}">
                                                    <tr>
                                                        <th scope="row">${r.appointmentId}</th>
                                                        <td>Dr. ${r.doctorName}</td>
                                                        <td><span class="badge badge-success">${r.degree}</span></td>
                                                        <td>${r.time}</td>
                                                        <td>${r.date}</td>
                                                        <td><button class="btn btn-danger btn-sm">Informe</button></td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!--Reporte 4-->
                    <div class="card">
                        <div class="card-header" id="headingThree">
                            <h2 class="mb-0">
                                <button class="btn btn-link btn-block text-left collapsed" id="patient4" type="button" data-toggle="collapse" data-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                    Consultas realizadas con un médico en específico dentro de un intervalo de tiempo.
                                </button>
                            </h2>
                        </div>
                        <div id="collapseFour" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col">
                                        <form action="ReportOperation" method="post">
                                            <div class="form-row my-2">
                                                <div class="col-12 col-md-4 my-2" id="for-exam">
                                                    <select name="doctor" class="form-control" id="doctor" required>
                                                        <option value="" selected>Choose...</option>
                                                        <c:forEach var="d" items="${doctorsPatient}">
                                                            <option value="${d.doctorId}">${d.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="col-12 col-md-3 my-2" id="for-date1">
                                                    <input type="date" class="form-control" name="date3" id="date3" required>
                                                </div>
                                                <div class="col-12 col-md-3 my-2" id="for-date2">
                                                    <input type="date" class="form-control" name="date4" id="date4" required>
                                                </div>
                                                <div class="col-12 col-md-2 my-2">
                                                    <button type="submit" class="btn btn-info btn-block" name="action" value="patient4">Buscar</button>
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
                                                    <th scope="col">Id Cita</th>
                                                    <th scope="col">Doctor</th>
                                                    <th scope="col">Tipo</th>
                                                    <th scope="col">Hora</th>
                                                    <th scope="col">Fecha</th>
                                                    <th scope="col">Informe</th>
                                                </tr>
                                            </thead>
                                            <tbody>                                                    
                                                <c:forEach var="r" items="${patient4}">
                                                    <tr>
                                                        <th scope="row">${r.appointmentId}</th>
                                                        <td>Dr. ${r.doctorName}</td>
                                                        <td><span class="badge badge-success">${r.degree}</span></td>
                                                        <td>${r.time}</td>
                                                        <td>${r.date}</td>
                                                        <td><button class="btn btn-danger btn-sm">Informe</button></td>
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
            <c:if test="${patient2 != null}">
                $('#patient2').click();
                $('#date1').val('${date1}');
                $('#date2').val('${date2}');
                $('#exam').val('${examId}');
            </c:if>

            <c:if test="${patient4 != null}">
                $('#patient4').click();
                $('#date3').val('${date3}');
                $('#date4').val('${date4}');
                $('#doctor').val('${doctorId}');
            </c:if>
            };
        </script>
    </body>
</html>
