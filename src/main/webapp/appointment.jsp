
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css/" %>
        <title>New Appointment</title>
    </head>
    <body>

        <!--NavBar-->

        <!--Nueva cita-->
        <section id="nueva-cita" class="my-4 py-2">
            <div class="container">
                <div class="row text-center">
                    <div class="col">
                        <h2 class="text-primary">Agendar Cita Médica</h2>
                    </div>
                </div>

                <div class="row my-3" id="row-especialidad">
                    <div class="col">
                        <form action="MainController" method="get">
                            <div class="form-row align-items-center">
                                <div class="col-auto ml-2">
                                    <label for="specialties">Especialidad</label>
                                </div>
                                <div class="col-4 my-1">
                                    <select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="specialties">
                                        <option value="0" selected>Choose...</option>
                                        <c:forEach var="s" items="${specialties}">
                                            <option value="${s.specialtyId}">${s.degree}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col my-1 ml-2">
                                    <button type="submit" name="action" value="d_specialties" class="btn btn-info">Buscar</button>  
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <div id="doctors" class="px-2 my-4">
                    <div class="row">
                        <div class="col text-center">
                            <h3 class="text-success">Doctores</h3>
                        </div>
                    </div>
                    <div class="row">
                        <c:forEach var="d" items="${doctors}">
                            <div class="col-12 col-md-2 col-lg-3">
                                <div class="card" style="width: 18rem;">
                                    <img src="..." class="card-img-top" alt="...">
                                    <div class="card-body">
                                        <h5 class="card-title">Card title</h5>
                                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                                    </div>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">Cras justo odio</li>
                                        <li class="list-group-item">Dapibus ac facilisis in</li>
                                        <li class="list-group-item">Vestibulum at eros</li>
                                    </ul>
                                    <div class="card-body">
                                        <a href="#" class="card-link">Card link</a>
                                        <a href="#" class="card-link">Another link</a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
    </section>


    <%@include file="js.html" %>
</body>
</html>
