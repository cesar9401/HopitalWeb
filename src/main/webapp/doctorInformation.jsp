
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                            <h1 class="text-info" id="title">Nuevo Doctor</h1>
                        </div>
                    </div>

                    <form action="AdminController" method="post">
                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <label for="id">Id</label>
                                <input type="text" class="form-control" id="doctorId" name="doctorId" required>
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
                                <label for="collegiate">Colegiado</label>
                                <input type="number" class="form-control" id="collegiate" name="collegiate" required>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md">
                            <c:forEach var="s" items="${specialties}">
                                <input type="checkbox" name="docSpecialties" id="${s.specialtyId}" value="${s.specialtyId}">
                                <label>${s.degree}</label>
                                <br/>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="date">Fecha</label>
                            <input type="date" id="date" name="date" class="form-control" required>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="startTime">Hora de Ingreso</label>
                            <input type="time" id="startTime" name="startTime" class="form-control" required>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="startTime">Hora de Salida</label>
                            <input type="time" id="endTime" name="endTime" class="form-control" required>
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
                            <button type="submit" name="action" value="addDoctor" id="btn-submit" class="btn btn-primary btn-block">Agregar</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>

        <%@include file="js.html" %>
        <c:if test="${doctor != null}">
            <script type="text/javascript">
                var btn = document.getElementById('btn-submit');
                btn.value = "editDoctor";
                btn.textContent = "Editar";

                window.onload = function () {
                    $('#doctorId').prop('readonly', true);

                    $('#doctorId').val("${doctor.doctorId}");
                    $('#name').val("${doctor.name}");
                    $('#phone').val("${doctor.phone}");
                    $('#dpi').val("${doctor.dpi}");
                    $('#collegiate').val("${doctor.collegiate}");
                    $('#date').val("${doctor.startDate}");
                    $('#startTime').val("${doctor.startTime}");
                    $('#endTime').val("${doctor.endTime}");
                    $('#email').val("${doctor.email}");
                    $('#pass').val("${doctor.pass}");

                <c:forEach var="s" items="${doctor.specialties}">
                    $('#${s.specialtyId}').attr('checked', true);
                </c:forEach>
                };
            </script>
        </c:if>
    </body>
</html>
