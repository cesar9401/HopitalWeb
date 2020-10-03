

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css.html" %>
        <title>Reservar Cita</title>
    </head>
    <body>

        <!--NavBar-->
        <jsp:include page="WEB-INF/navPatient.jsp"></jsp:include>

            <!--Titulo-->
            <div class="container">
                <div class="row my-4">
                    <div class="col text-center">
                        <h1 class="text-success display-4">Agendar Cita</h1>
                    </div>
                </div>
            </div>

            <!--Datos doctor-->
            <div class="jumbotron jumbotron-fluid">
                <div class="container">
                    <h3 class="display-4 my-0">Dr. ${doctor.name}</h3>
                <p class="lead my-0">Horario: ${doctor.startTime} - ${doctor.endTime}</p>
                <p class="lead my-0">Email: ${doctor.email}</p>
                <c:forEach var="s" items="${doctor.specialties}">
                    <p class="lead my-0"><span class="badge badge-info">${s.degree}</span></p>
                    </c:forEach>
            </div>
        </div>

        <!--Formularios para agendar citas-->
        <section id="appointments-doctor">
            <div class="container my-4">
                <div class="row my-4 align-items-center">

                    <!--Formulario para cambiar fecha-->
                    <div class="col-12 col-md-4 text-right">
                        <h1 class="display-3 my-0">Citas</h1>
                        <form action="MainController" method="post">
                            <div class="form-group row my-2">
                                <div class="col">
                                    <input type="date" name="date" class="form-control-lg" value="${date}" required>
                                    <input type="hidden" name="doctorId" value="${doctor.doctorId}">
                                </div>
                            </div>
                            <div class="form-group row my-2">
                                <div class="col">
                                    <button type="submit" class="btn btn-info" name="action" value="changeDate">Cambiar Fecha</button>
                                </div>
                            </div>
                        </form>
                    </div>

                    <!--Formulario para agendar cita-->
                    <div class="col-12 col-md-8">
                        <form action="MainController" method="post">
                            <input type="hidden" name="doctorId" value="${doctor.doctorId}">
                            <input type="hidden" name="patientId" value="${profile.patientId}">
                            <div class="form-group row form-control-lg">
                                <div class="col text-right">
                                    <label for="specialty" class="col col-form-label">Especialidad</label>
                                </div>
                                <div class="col">
                                    <select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="specialty" required>
                                        <option value="" selected>Choose...</option>
                                        <c:forEach var="s" items="${doctor.specialties}">
                                            <option value="${s.specialtyId}">${s.degree}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <c:forEach var="a" items="${appointments}">
                                <c:choose>
                                    <c:when test="${a.isAvailable}">
                                        <div class="form-group row text-center my-2">
                                            <div class="col">
                                                <div class="form-check form-control-lg">
                                                    <input class="form-check-input" type="radio" name="AppTime" id="exampleRadios1" value="${a.time}" required="">
                                                    <label class="form-check-label" for="exampleRadios1">
                                                        <span class="badge badge-pill badge-light">Inicio consulta</span> ${a.time} <span class="badge badge-pill badge-success">Disponible</span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="form-group row text-center my-2">
                                            <div class="col">
                                                <div class="form-check form-control-lg">
                                                    <input class="form-check-input" type="radio" name="AppTime" id="exampleRadios1" value="${a.time}" disabled required>
                                                    <label class="form-check-label" for="exampleRadios1">
                                                        <span class="badge badge-pill badge-light">Inicio consulta</span> ${a.time} <span class="badge badge-danger">Ocupado</span>
                                                        <c:if test="${a.patientId == profile.patientId}">
                                                            <span class="badge badge-warning">Mi cita</span>
                                                        </c:if>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <div class="form-group row my-4 text-center">
                                <div class="col-8 col-md-4 offset-md-4">
                                    <button type="submit" name="action" value="newAppointment" class="btn btn-primary btn-block">Agendar Cita</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Cita Agendada</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Su cita se agendo con exito para el dia <span class="font-weight-bold">${appTmp.date}</span> a las <span class="font-weight-bold">${appTmp.time}</span> horas.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="js.html" %>
        <c:if test="${success != null}">
            <script type="text/javascript">
                $(document).ready(function () {
                    $('#exampleModal').modal('show');
                });
            </script>        
        </c:if>
    </body>
</html>
