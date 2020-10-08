
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet"/>
        <%@include file="css.html" %>
        <title>Agendar Cita</title>
    </head>
    <body>
        <!--NavBar-->
        <jsp:include page="WEB-INF/navPatient.jsp"></jsp:include>

            <section id="form-search">
                <div class="container">
                    <div class="row">
                        <div class="col text-center">
                            <h1 class="display-4 text-info">Examen de Laboratorio</h1>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col">
                            <form action="LabController" method="post">
                                <div class="row my-2">
                                    <div class="form-group col-12 col-md-4 col-lg-5 my-2">
                                        <label for="exam">Examen</label>
                                        <select id="exam" name="exam" class="custom-select custom-select-lg" required>
                                            <option value="" selected>Choose...</option>
                                        <c:forEach var="e" items="${exams}">
                                            <option value="${e.examId}">${e.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col-12 col-md-4 col-lg-5 my-2">
                                    <label for="doctor">Doctor</label>
                                    <select id="doctor" name="doctor" class="custom-select custom-select-lg" required>
                                        <option value="" selected>Choose...</option>
                                        <c:forEach var="d" items="${doctors}">
                                            <option value="${d.doctorId}">Dr(a). ${d.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col-12 col-md-4 col-lg-2 my-2">
                                    <label for="date">Fecha</label>
                                    <input type="date" name="date" id="date" class="" required>
                                </div>
                            </div>
                            <div class="row my-2">
                                <div class="form-group col-12 col-md-4 col-lg-2 offset-lg-10 text-center">
                                    <button type="submit" name="action" value="getInfoApp" class="btn btn-outline-info btn-block">Buscar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>

        <c:if test="${appointmentsLab != null}">
            <section id="app-lab">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <form action="LabController" method="post" enctype="multipart/form-data">
                                <input type="hidden" id="doctorId" name="doctorId">
                                <input type="hidden" id="patientId" name="patientId">
                                <input type="hidden" id="examId" name="examId">
                                <input type="hidden" id="date-exam" name="date-exam">
                                <input type="hidden" id="exam-order" name="order">
                                <div class="form-group row my-2 text-center">
                                    <c:forEach var="a" items="${appointmentsLab}">
                                        <div class="col-6">
                                            <div class="form-check form-control-lg">
                                                <c:choose>
                                                    <c:when test="${a.isAvailable}">
                                                        <input class="form-check-input" type="radio" name="AppTime" id="exampleRadios1" value="${a.time}" required>
                                                        <label class="form-check-label" for="exampleRadios1">
                                                            <span class="badge badge-pill badge-light">Inicio consulta</span> ${a.time} <span class="badge badge-pill badge-success">Disponible</span>
                                                        </label>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input class="form-check-input" type="radio" name="AppTime" id="exampleRadios1" value="${a.time}" disabled required>
                                                        <label class="form-check-label" for="exampleRadios1">
                                                            <span class="badge badge-pill badge-light">Inicio consulta</span> ${a.time} <span class="badge badge-danger">Ocupado</span>
                                                            <c:if test="${a.patientId == profile.patientId}">
                                                                <span class="badge badge-warning">Mi examen</span>
                                                            </c:if>
                                                        </label>
                                                    </c:otherwise>
                                                </c:choose>

                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="form-group row my-4 text-center" id="exam-file">
                                    <div class="col-12 col-md-8 offset-md-2">
                                        <label for="exampleFormControlFile1">Orden(Formato PDF)</label>
                                        <input type="file" class="form-control-file text-center" id="exampleFormControlFile1" accept=".pdf" name="file">
                                    </div>
                                </div>

                                <div class="form-group row my-4 text-center">
                                    <div class="col-12 col-md-4 offset-md-4">
                                        <button type="submit" name="action" value="setAppLab" class="btn btn-primary btn-block">Agendar Cita</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </c:if>

        <!-- Modal para confirmar cita en laboratorio-->
        <div class="modal fade" id="modal-lab" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Cita Agendada</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p id="info-modal-lab"></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="js.html" %>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
        <script type="text/javascript">
            jQuery(document).ready(function ($) {
                $(document).ready(function () {
                    $('#exam').select2();
                    $('#doctor').select2();
                });
            });
        </script>
        <c:if test="${exam != null}">
            <script>
                window.onload = function () {
                    $('#exam').val('${exam.examId}');
                    $('#doctor').val('${doctorId}');
                    $('#date').val('${date}');

                    $('#examId').val('${exam.examId}');
                    $('#doctorId').val('${doctorId}');
                    $('#date-exam').val('${date}');
                    $('#patientId').val('${profile.patientId}');
                    $('#exam-order').val('${exam.order}');
                <c:choose>
                    <c:when test="${exam.order}">
                    $('#exam-file').prop('hidden', false);
                    $('#exampleFormControlFile1').prop('required', true);
                    </c:when>
                    <c:otherwise>
                    $('#exam-file').prop('hidden', true);
                    $('#exampleFormControlFile1').prop('required', false);
                    </c:otherwise>
                </c:choose>
                };
            </script>
        </c:if>

        <c:if test="${myApp != null}">
            <script>
                var p = document.getElementById('info-modal-lab');
                p.textContent = "Se ha agendado correctamente su cita para el dia ${myApp.date} a las ${myApp.time}";
                $(document).ready(function () {
                    $('#modal-lab').modal('show');
                });
            </script>
        </c:if>
    </body>
</html>
