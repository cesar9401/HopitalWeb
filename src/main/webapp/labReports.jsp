
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
        <!--Nab Bar-->
        <jsp:include page="WEB-INF/navLabWorker.jsp"></jsp:include>

            <section id="reports-lab">
                <div class="container">
                    <div class="row">
                        <div class="col text-center">
                            <h1 class="text-info display-4">Reportes</h1>
                            <h1 class="text-info display-3">Lab. ${profile.name}</h1>
                        <p class="lead my-4">
                            <span class="font-weight-bold">Dias de Trabajo: </span>
                            <c:forEach var="d" items="${profile.days}">
                                <span class="badge badge-light">${d.day} </span>
                            </c:forEach>
                        </p>
                        <p class="lead my-4 text-info">Examen: ${e}</p>
                    </div>
                </div>
            </div>

            <div class="accordion" id="accordionExample">
                <div class="container">

                    <!-- Reporte 1 -->
                    <div class="card">
                        <div class="card-header" id="headingOne">
                            <h2 class="mb-0">
                                <button class="btn btn-link btn-block text-left" id="lab1" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    Reporte de exámenes a realizarse en su turno del día
                                </button>
                            </h2>
                        </div>

                        <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col">
                                        <form action="ReportOperation" method="post">
                                            <div class="form-row my-2">
                                                <div class="col my-2" id="for-date1">
                                                    <input type="date" class="form-control" name="date1" id="date1" required>
                                                </div>
                                                <div class="col my-2">
                                                    <button type="submit" class="btn btn-info btn-block" name="action" value="lab1">Buscar</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col">
                                        <c:if test="${lab1 != null}">
                                            <c:choose>
                                                <c:when test="${work}">
                                                    <table class="table table-striped">
                                                        <thead>
                                                            <tr>
                                                                <th scope="col">#</th>
                                                                <th scope="col">Paciente</th>
                                                                <th scope="col">Solicita</th>
                                                                <th scope="col">Examen</th>
                                                                <th scope="col">Hora</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="a" items="#{lab1}">
                                                                <c:if test="${!a.status}">
                                                                    <tr>
                                                                        <th scope="row">${a.appointmentId}</th>
                                                                        <td>${a.patientName}</td>
                                                                        <td>Dr. ${a.doctorName}</td>
                                                                        <td><span class="badge badge-info">${a.examName}</span></td>
                                                                        <td>${a.time}</td>
                                                                    </tr>   
                                                                </c:if>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </c:when>
                                                <c:otherwise>
                                                    <h1 class="text-danger display-3 text-center">Feliz Descanso :)</h1>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!--Reporte 2-->
                    <div class="card">
                        <div class="card-header" id="headingTwo">
                            <h2 class="mb-0">
                                <button class="btn btn-link btn-block text-left collapsed" id="lab2" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    Reporte de reporte de exámenes realizados en el día
                                </button>
                            </h2>
                        </div>
                        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col">
                                        <form action="ReportOperation" method="post">
                                            <div class="form-row my-2">
                                                <div class="col my-2" id="for-date2">
                                                    <input type="date" class="form-control" name="date2" id="date2" required>
                                                </div>
                                                <div class="col my-2">
                                                    <button type="submit" class="btn btn-info btn-block" name="action" value="lab2">Buscar</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col">
                                        <c:if test="${lab2 != null}">
                                            <c:choose>
                                                <c:when test="${work2}">
                                                    <table class="table table-striped">
                                                        <thead>
                                                            <tr>
                                                                <th scope="col">#</th>
                                                                <th scope="col">Paciente</th>
                                                                <th scope="col">Solicita</th>
                                                                <th scope="col">Examen</th>
                                                                <th scope="col">Hora</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="a" items="#{lab2}">
                                                                <c:if test="${a.status}">
                                                                    <tr>
                                                                        <th scope="row">${a.appointmentId}</th>
                                                                        <td>${a.patientName}</td>
                                                                        <td>Dr. ${a.doctorName}</td>
                                                                        <td><span class="badge badge-info">${a.examName}</span></td>
                                                                        <td>${a.time}</td>
                                                                    </tr>   
                                                                </c:if>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </c:when>
                                                <c:otherwise>
                                                    <h1 class="text-danger display-3 text-center">Feliz Descanso :)</h1>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <!--Reporte 3-->
                    <!--
                        <div class="card">
                            <div class="card-header" id="headingThree">
                                <h2 class="mb-0">
                                    <button class="btn btn-link btn-block text-left collapsed" id="lab3" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                        Reporte de utilización de cada día de trabajo, dentro de un intervalo de tiempo
                                    </button>
                                </h2>
                            </div>
                            <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
                                <div class="card-body">
                                    Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                                </div>
                            </div>
                        </div>
                    -->

                    <!--Reporte 4-->
                    <div class="card">
                        <div class="card-header" id="headingThree">
                            <h2 class="mb-0">
                                <button class="btn btn-link btn-block text-left collapsed" id="lab4" type="button" data-toggle="collapse" data-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                    Reporte de las 10 fechas con más trabajo realizado
                                </button>
                            </h2>
                        </div>
                        <div id="collapseFour" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col">
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Date</th>
                                                    <th scope="col">Id Examen</th>
                                                    <th scope="col">Examen</th>
                                                    <th scope="col">Cantidad</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="r" items="${lab4}">
                                                    <tr>
                                                        <th scope="row">${r.date}</th>
                                                        <td>${r.examId}</td>
                                                        <td>${r.examName}</td>
                                                        <td>${r.quantity}</td>
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
            <c:if test="${lab1!= null}">
                $('#lab1').click();
                $('#date1').val("${date1}");
            </c:if>

            <c:if test="${lab2!= null}">
                $('#lab2').click();
                $('#date2').val("${date2}");
            </c:if>
            };
        </script>
    </body>
</html>
