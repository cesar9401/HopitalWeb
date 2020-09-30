<%-- 
    Document   : doctorView
    Created on : Sep 29, 2020, 1:22:26 AM
    Author     : cesar31
--%>

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

            <!--Datos doctor-->
            <div class="jumbotron jumbotron-fluid text-center">
                <div class="container">
                    <h3 class="display-4 my-0">Dr. ${profile.name}</h3>
                <p class="lead my-0">Horario: ${profile.startTime} - ${profile.endTime}</p>
                <p class="lead my-0">Email: ${profile.email}</p>
                <c:forEach var="s" items="${profile.specialties}">
                    <p class="lead my-0"><span class="badge badge-info">${s.degree}</span> <span class="badge badge-light">Q${s.priceConsultation}</span></p>
                </c:forEach>
                <hr class="my-4">
                <p class="lead my-0">HOSPITAL PASTEUR - Ciudad de Quetzaltenango, Guatemala</p>
            </div>
        </div>    

        <!--Citas del dia-->
        <section id="citasHoy">
            <div class="container">
                <div class="row">
                    <div class="col text-center">
                        <h1 class="display-3 text-info my-2">Citas del D&iacute;a</h1>
                    </div>
                </div>

                <div class="row my-2 align-items-center">
                    <!--Formulario para cambiar de fecha-->
                    <div class="col-12 col-md-3 text-right">
                        <h1 class="my-0">Fecha</h1>
                        <form action="DoctorController" method="post">
                            <div class="form-group row my-2">
                                <div class="col">
                                    <input type="date" name="date" class="form-control-lg" value="${date}" required>
                                    <input type="hidden" name="doctorId" value="${profile.doctorId}">
                                </div>
                            </div>
                            <div class="form-group row my-2">
                                <div class="col">
                                    <button type="submit" class="btn btn-info" name="action" value="changeDateDoctor">Cambiar Fecha</button>
                                </div>
                            </div>
                        </form>
                    </div>

                    <div class="col-12 col-md-9 text-center">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Paciente</th>
                                    <th scope="col">Tipo Consulta</th>
                                    <th scope="col">Hora</th>
                                    <th scope="col">Atender</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="a" items="${appDoc}">
                                    <c:set var="status" value=""></c:set>
                                    <c:if test="${a.status || a.isAvailable}">
                                        <c:set var="status" value="disabled"></c:set>
                                    </c:if>
                                    <tr>
                                        <th scope="row">${a.patientId}</th>
                                        <td>${a.patientName}</td>
                                        <td><span class="badge badge-success">${a.degree}</span></td>
                                        <td>${a.time}</td>
                                        <td><a href="DoctorController?action=${a.appointmentId}" class="btn btn-primary btn-sm ${status}">Crear Reporte</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </section>

        <!-- Modal -->
        <div class="modal fade" id="reportModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Informaci&oacute;n</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Registro ingresado con exito.</p>
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
                    $('#reportModal').modal('show');
                });
            </script>        
        </c:if>
    </body>
</html>
