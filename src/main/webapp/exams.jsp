
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

            <div class="container">
                <div class="row my-4">
                    <div class="col text-center">
                        <h1 class="text-primary">Examenes</h1>
                    </div>
                </div>

                <div class="row my-4">
                    <div class="col">
                        <!-- Formulario aqui -->
                        <form action="DoctorController" method="post">
                            <div class="form-row my-0">
                                <div class="col-12 col-md-3 my-2">
                                    <select id="inputType" class="form-control" name="kind" required>
                                        <option value="0" selected>Nombre</option>
                                        <option value="1">Precio (Igual)</option>
                                        <option value="2">Precio (Mayor o Igual)</option>
                                        <option value="3">Precio (Menor o Igual)</option>
                                    </select>
                                </div>
                                <div class="col-12 col-md-6 my-2">
                                    <input id="inputSearch" type="text" name="search" class="form-control" placeholder="Nombre" required>
                                </div>
                                <div class="col-12 col-md-2 my-2">
                                    <button type="submit" class="btn btn-info btn-block" name="action" value="searchExams">Buscar</button>
                                </div>
                                <div class="col-12 col-md-1 my-2">
                                    <a href="#" onclick="newExam()" class="btn btn-outline-info btn-block" data-toggle="modal" data-target="#modalExam">Agregar</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!--Examenes-->
            <section id="exams-admin">
                <div class="container">
                    <div class="row">
                    <c:forEach var="e" items="${exams}">
                        <div class="col-12 col-md-6 col-lg-4 my-3">
                            <div class="card">
                                <div class="card-header text-center">
                                    <h3 class="text-info">${e.name}</h3>
                                </div>
                                <ul class="list-group list-group-flush">
                                    <c:if test="${e.order}">
                                        <li class="list-group-item text-center"><span class="badge badge-warning">Requiere orden</span></li>
                                        </c:if>
                                    <li class="list-group-item"><span class="font-weight-bold">Descripci&oacute;n:</span> ${e.description}</li>
                                        <c:choose>
                                            <c:when test="${e.report}">
                                            <li class="list-group-item"><span class="font-weight-bold">Tipo Reporte</span> <span class="badge badge-danger">PDF</span></li>
                                            </c:when>
                                            <c:otherwise>
                                            <li class="list-group-item"><span class="font-weight-bold">Tipo Reporte</span> <span class="badge badge-success">IMAGEN</span></li>
                                            </c:otherwise>
                                        </c:choose>
                                    <li class="list-group-item"><span class="font-weight-bold">Precio:</span> Q.${e.price}</li>
                                    <li class="list-group-item text-center">
                                        <a href="#" onclick="setExam('${e.examId}', '${e.name}', '${e.order}', '${e.description}', '${e.price}', '${e.report}')" class="btn btn-outline-info" data-toggle="modal" data-target="#modalExam">Editar</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>

        <!-- Modal para editar examenes-->
        <div class="modal fade" id="modalExam" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Editar Especialidad</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="DoctorController" method="post">
                            <div class="form-group">
                                <label for="name">Nombre</label>
                                <input type="text" class="form-control" id="name" name="name" placeholder="Nombre" required>
                                <input type="hidden" id="examId" name="examId">
                            </div>
                            <div class="form-group">
                                <label for="order">Requiere Orden</label>
                                <select id="order" name="order" class="form-control" required>
                                    <option selected>Choose...</option>
                                    <option value="true">Si</option>
                                    <option value="false">No</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="order">Tipo de Reporte</label>
                                <select id="report" name="report" class="form-control" required>
                                    <option selected>Choose...</option>
                                    <option value="true">PDF</option>
                                    <option value="false">Imagen</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="description">Descripci&oacute;n</label>
                                <textarea class="form-control" id="description" name="description" placeholder="Agregue una descripción aquí" required></textarea>
                            </div>
                            <div class="form-group">
                                <label for="price">Precio Q.</label>
                                <input type="number" step="any" min="0" class="form-control" id="price" name="price" placeholder="Precio Q." required>
                            </div>
                            <button type="submit" class="btn btn-primary" id="btn-submit" name="action" value="editExam">Editar</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <!--Moda para informar sobre cambios en examenes-->
        <div class="modal fade" id="modalInfo" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title text-center" id="exampleModalLabel">Información</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p id="modal-info">Se ha actualizado correctamente el examen: ${examUp}</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="js.html" %>
        <script type="text/javascript" src="js/script.js"></script>
        
        <c:if test="${examUp != null}">
            <script type="text/javascript">
                $(document).ready(function () {
                    $('#modalInfo').modal('show');
                });
            </script>        
        </c:if>

        <c:if test="${newEx != null}">
            <script type="text/javascript">
                var p = document.getElementById('modal-info');
                p.textContent = "Se ha ingresado correctamente el examen ${newEx}";
                $(document).ready(function () {
                    $('#modalInfo').modal('show');
                });
            </script> 
        </c:if>
    </body>
</html>
