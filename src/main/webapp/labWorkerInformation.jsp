
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css.html" %>
        <title>Administrador - ${profile.name}</title>
    </head>
    <body>

        <!--NavBar-->
        <jsp:include page="WEB-INF/navAdmin.jsp"></jsp:include>

            <!--Formulario Aqui-->
            <section id="mainDoc">
                <div class="container my-4">
                    <div class="row">
                        <div class="col text-center">
                            <h1 class="text-info" id="title">Nuevo Laboratorista</h1>
                        </div>
                    </div>

                    <form action="LabController" method="post">
                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <label for="labWorkerId">Id</label>
                                <input type="text" class="form-control" id="labWorkerId" name="labWorkerId" required>
                            </div>
                            <div class="form-group col-md-5">
                                <label for="name">Nombre</label>
                                <input type="text" class="form-control" id="name" name="name" required>
                            </div>
                            <div class="form-group col-md-4">
                                <label for="phone">Telefono</label>
                                <input type="number" class="form-control" id="phone" name="phone" required>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="dpi">DPI</label>
                                <input type="number" class="form-control" id="dpi" name="dpi" required>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="registryNumber">Registro de Salud</label>
                                <input type="text" class="form-control" id="registryNumber" name="registryNumber" required>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="days">Dias de Trabajo</label><hr>
                            <c:forEach var="d" items="${days}">
                                <input type="checkbox" name="days" id="${d.dayId}" value="${d.dayId}">
                                <label>${d.day}</label>
                                <br/>
                            </c:forEach>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="exams">Examenes</label>
                            <select id="exams" class="form-control" name="exams">
                                <option value="" selected>Choose...</option>
                                <c:forEach var="e" items="${exams}">
                                    <option value="${e.examId}">${e.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="date">Fecha</label>
                            <input type="date" id="date" name="date" class="form-control" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="inputEmail">Email</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="pass" name="pass" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-2 offset-md-5">
                            <button type="submit" name="action" value="addLabWorker" id="btn-submit" class="btn btn-primary btn-block">Agregar</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>

        <%@include file="js.html" %>
        <c:if test="${labWorker != null}">
            <script type="text/javascript">
                var btn = document.getElementById('btn-submit');
                btn.value = "editLabWorker";
                btn.textContent = "Editar";

                window.onload = function () {
                    $('text-info').text('Editar Laboratorista');
                    $('#labWorkerId').prop('readonly', true);

                    $('#labWorkerId').val("${labWorker.labWorkerId}");
                    $('#name').val("${labWorker.name}");
                    $('#phone').val("${labWorker.phone}");
                    $('#dpi').val("${labWorker.dpi}");
                    $('#registryNumber').val("${labWorker.registry}");
                    $('#date').val("${labWorker.startDate}");
                    $('#email').val("${labWorker.email}");
                    $('#pass').val("${labWorker.pass}");

                    $('#exams').val("${labWorker.examId}");
                <c:forEach var="d" items="${labWorker.days}">
                    $('#${d.dayId}').prop('checked', true);
                </c:forEach>
                };
            </script>
        </c:if>
    </body>
</html>
