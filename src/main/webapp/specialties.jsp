
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
                        <h1 class="text-primary">Especialidades</h1>
                    </div>
                </div>

                <div class="row my-4">
                    <div class="col">
                        <!--Formulario aqui-->
                        <form action="DoctorController" method="post">
                            <div class="form-row my-0">
                                <div class="col-12 col-md-3 my-2">
                                    <select id="inputType" class="form-control" name="kind" required>
                                        <option value="0" selected>Especialidad</option>
                                        <option value="1">Precio (Igual)</option>
                                        <option value="2">Precio (Mayor o Igual)</option>
                                        <option value="3">Precio (Menor o Igual)</option>
                                    </select>
                                </div>
                                <div class="col-12 col-md-6 my-2">
                                    <input id="inputSearch" type="text" name="search" class="form-control" placeholder="Especialidad" required>
                                </div>
                                <div class="col-12 col-md-2 my-2">
                                    <button type="submit" class="btn btn-info btn-block" name="action" value="searchSpecialties">Buscar</button>
                                </div>
                                <div class="col-12 col-md-1 my-2">
                                    <a href="#" onclick="addSpecialty()" class="btn btn-outline-info" data-toggle="modal" data-target="#modalSpecialty">Agregar</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!--Especialidades-->
            <section id="spacialties">
                <div class="container">
                    <div class="row">
                    <c:forEach var="s" items="${specialties}">
                        <div class="col-12 col-md-6 col-lg-4 my-3">
                            <div class="card">
                                <div class="card-header text-center">
                                    <h3 class="text-info">${s.degree}</h3>
                                </div>
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item text-center"><span class="font-weight-bold">Precio: Q</span>${s.priceConsultation}</li>
                                    <li class="list-group-item text-center">
                                        <a href="#" onclick="setSpecialty('${s.specialtyId}', '${s.degree}', '${s.priceConsultation}')" class="btn btn-outline-success" data-toggle="modal" data-target="#modalSpecialty">Editar</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>

        <!-- Modal para editar especialidades-->
        <div class="modal fade" id="modalSpecialty" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                                <label for="degree">Titulo</label>
                                <input type="text" class="form-control" id="degree" name="degree" placeholder="Especialidad" required>
                                <input type="hidden" id="specialtyId" name="specialtyId">
                            </div>
                            <div class="form-group">
                                <label for="price">Precio Q.</label>
                                <input type="number" step="any" min="0" class="form-control" id="price" name="price" placeholder="Precio Q." required>
                            </div>
                            <button type="submit" id="btn-submit-specialty" class="btn btn-primary" name="action" value="editSpecialty">Editar</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <!--Moda para informar sobre cambios en especialidades-->
        <div class="modal fade" id="modalInfo" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Información</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p id="modal-info-specialties">Se ha actualizado correctamente la Especialidad: ${update.degree}</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="js.html" %>
        <script type="text/javascript" src="js/script.js"></script>

        <c:if test="${update != null}">
            <script type="text/javascript">
                $(document).ready(function () {
                    $('#modalInfo').modal('show');
                });
            </script>        
        </c:if>

        <c:if test="${newSpecialty != null}">
            <script type="text/javascript">
                var p = document.getElementById("modal-info-specialties");
                p.textContent = "Se ha agregado correctamente la nueva especializacion ${newSpecialty}";
                $(document).ready(function () {
                    $('#modalInfo').modal('show');
                });
            </script>
        </c:if>
    </body>
</html>
