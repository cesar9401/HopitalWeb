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
            <div class="jumbotron text-center py-4">
                <div class="container">
                    <h1 class="text-success my--0">Laboratorista: ${profile.name}</h1>
                <p class="lead my-0">
                    <span class="font-weight-bold">Dias de Trabajo: </span>
                    <c:forEach var="d" items="${profile.days}">
                        <span class="badge badge-info">${d.day} </span>
                    </c:forEach>
                </p>
                <p class="lead my-0"><span class="font-weight-bold">Registro de Salud: </span>${profile.registry}</p>
                <hr class="my-2">
                <!--Datos Examen-->
                <h2 class="text-danger my-0">Examen: ${exam.name}</h2>
                <p class="lead font-weight-bold my-0">Descripci&oacute;n</p>
                <p class="lead my-0">${exam.description}</p>
                <p class="lead my-0"><span class="font-weight-bold">Precio: </span><span class="badge badge-primary">Q.${exam.price}</span></p>
                <hr class="my-4">
                <p class="lead my-0">HOSPITAL PASTEUR - Ciudad de Quetzaltenango, Guatemala</p>
            </div>
        </div>

        <!--Citas del dia-->
        <section id="exams-lab">
            <div class="container">
                <div class="row">
                    <div class="col text-center">
                        <h2 class="display-4 text-info">Citas Laboratorio</h2>
                    </div>
                </div>

                <div class="row my-2">
                    <!--Formulario para cambio de fecha-->
                    <div class="col-12 col-md-3 text-right">
                        <h1 class="my-0">Fecha</h1>
                        <form action="LabController" method="post">
                            <div class="form-group row my-2">
                                <div class="col">
                                    <input type="date" name="date" class="form-control-lg" value="${date}" required>
                                    <input type="hidden" name="labId" value="${profile.labWorkerId}">
                                </div>
                            </div>
                            <div class="form-group row my-2">
                                <div class="col">
                                    <button type="submit" class="btn btn-info" name="action" value="changeDateLab">Cambiar Fecha</button>
                                </div>
                            </div>
                        </form>
                    </div>

                    <c:choose>
                        <c:when test="${work}">
                            <div class="col-12 col-md-9 text-center">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Paciente</th>
                                            <th scope="col">Solicita</th>
                                            <th scope="col">Hora</th>
                                                <c:if test="${exam.order}">
                                                <th scope="col">Orden</th>
                                                </c:if>
                                            <th scope="col">Opci&oacute;n</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="a" items="${appointmentsLab}">
                                            <c:set var="status" value=""></c:set>
                                            <c:set var="dr" value="Dr."></c:set>
                                            <c:if test="${a.status || a.isAvailable}">
                                                <c:set var="status" value="disabled"></c:set>
                                            </c:if>
                                            <c:if test="${a.isAvailable}">
                                                <c:set var="dr" value=""></c:set>
                                            </c:if>
                                            <tr>
                                                <th scope="row">${a.appointmentId}</th>
                                                <td>${a.patientName}</td>
                                                <td>${dr} ${a.doctorName}</td>
                                                <td>${a.time}</td>
                                                <c:if test="${exam.order}">
                                                    <td>
                                                        <c:if test="${!a.isAvailable}">
                                                            <a href="OperationsLab?action=file&value=${a.appointmentId}" class="btn btn-danger btn-sm" target="_blank">Orden</a>
                                                        </c:if>
                                                    </td>
                                                </c:if>
                                                <td><a href="OperationsLab?action=result&value=${a.appointmentId}" class="btn btn-primary btn-sm ${status}">Asignar Resultado</a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-12 col-md-9 text-center">
                                <h1 class="text-danger display-1">Feliz Descanso :)</h1>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </section>

        <!-- Modal para confirmar resultado de laboratorio-->
        <div class="modal fade" id="modal-lab" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Resultado Agregado</h5>
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
        <c:if test="${successResult != null}">
            <script>
                var p = document.getElementById('info-modal-lab');
                p.textContent = "Se ha ingresado correctamente el resultado para el paciente ${successResult}";
                $(document).ready(function () {
                    $('#modal-lab').modal('show');
                });
            </script>
        </c:if>
    </body>
</html>
