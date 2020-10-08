
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css.html" %>
        <title>Admin - ${profile.name}</title>
    </head>
    <body>
        <!--NavBar-->
        <jsp:include page="WEB-INF/navAdmin.jsp"></jsp:include>

            <section id="admin-reports">
                <div class="container">
                    <div class="row">
                        <div class="col text-center my-2">
                            <h1 class="text-info display-2 my-3">Reportes</h1>
                            <h1 class="text-info display-1 my-2">Admin. ${profile.name}</h1>
                    </div>
                </div>
            </div>

            <div class="accordion" id="accordionExample">
                <div class="container">

                    <!--Reporte 1-->
                    <div class="card">
                        <div class="card-header" id="headingOne">
                            <h2 class="mb-0">
                                <button class="btn btn-link btn-block text-left" id="admin1" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    Los 10 médicos que más informes han realizado dentro de un intervalo de tiempo
                                </button>
                            </h2>
                        </div>

                        <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col">
                                        <form action="ReportOperation" method="post">
                                            <div class="form-row my-2">
                                                <div class="col-12 col-md-5 my-2" id="for-date1">
                                                    <input type="date" class="form-control" name="date1" id="date1" required>
                                                </div>
                                                <div class="col-12 col-md-5 my-2" id="for-date2">
                                                    <input type="date" class="form-control" name="date2" id="date2" required>
                                                </div>
                                                <div class="col-12 col-md-2 my-2">
                                                    <button type="submit" class="btn btn-info btn-block" name="action" value="admin1">Buscar</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col">
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Id Doctor</th>
                                                    <th scope="col">Nombre</th>
                                                    <th scope="col">Cantidad</th>
                                                    <th scope="col">Fechas</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="r" items="${admin1}">
                                                    <tr>
                                                        <th scope="row">${r.doctorId}</th>
                                                        <td>Dr. ${r.doctorName}</td>
                                                        <td><span class="badge badge-primary">${r.quantity}</span></td>
                                                        <td>${r.dates}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!--Reporte 2-->
                    <div class="card">
                        <div class="card-header" id="headingTwo">
                            <h2 class="mb-0">
                                <button class="btn btn-link btn-block text-left collapsed" id="admin2" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    Ingresos obtenidos por médico en un intervalo de tiempo
                                </button>
                            </h2>
                        </div>
                        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col">
                                        <form action="ReportOperation" method="post">
                                            <div class="form-row my-2">
                                                <div class="col-12 col-md-5 my-2">
                                                    <input type="date" class="form-control" name="date3" id="date3" required>
                                                </div>
                                                <div class="col-12 col-md-5 my-2">
                                                    <input type="date" class="form-control" name="date4" id="date4" required>
                                                </div>
                                                <div class="col-12 col-md-2 my-2">
                                                    <button type="submit" class="btn btn-info btn-block" name="action" value="admin2">Buscar</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col">
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Id Doctor</th>
                                                    <th scope="col">Nombre</th>
                                                    <th scope="col">Total</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="r" items="${admin2}">
                                                    <tr>
                                                        <th scope="row">${r.doctorId}</th>
                                                        <td>Dr. ${r.doctorName}</td>
                                                        <td>Q.${r.total}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!--Reporte 3-->
                    <div class="card">
                        <div class="card-header" id="headingThree">
                            <h2 class="mb-0">
                                <button class="btn btn-link btn-block text-left collapsed" id="admin3" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                    Los 5 médicos con menor cantidad de citas dentro de un intervalo de tiempo
                                </button>
                            </h2>
                        </div>
                        <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col">
                                        <form action="ReportOperation" method="post">
                                            <div class="form-row my-2">
                                                <div class="col-12 col-md-5 my-2">
                                                    <input type="date" class="form-control" name="date5" id="date5" required>
                                                </div>
                                                <div class="col-12 col-md-5 my-2">
                                                    <input type="date" class="form-control" name="date6" id="date6" required>
                                                </div>
                                                <div class="col-12 col-md-2 my-2">
                                                    <button type="submit" class="btn btn-info btn-block" name="action" value="admin3">Buscar</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col">
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Id Doctor</th>
                                                    <th scope="col">Nombre</th>
                                                    <th scope="col">Colegiado</th>
                                                    <th scope="col">Ingreso</th>
                                                    <th scope="col">Salida</th>
                                                    <th scope="col">Cantidad</th>

                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="d" items="${admin3}">
                                                    <tr>
                                                        <th scope="row">${d.doctorId}</th>
                                                        <td>Dr. ${d.name}</td>
                                                        <td>${d.collegiate}</td>
                                                        <td>${d.startTime}</td>
                                                        <td>${d.endTime}</td>
                                                        <td>${d.quantity}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!--Reporte 4-->
                    <div class="card">
                        <div class="card-header" id="headingThree">
                            <h2 class="mb-0">
                                <button class="btn btn-link btn-block text-left collapsed" type="button" data-toggle="collapse" data-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                    Reporte #4
                                </button>
                            </h2>
                        </div>
                        <div id="collapseFour" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
                            <div class="card-body">
                                Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                            </div>
                        </div>
                    </div>

                    <!--Reporte 5-->
                    <div class="card">
                        <div class="card-header" id="headingThree">
                            <h2 class="mb-0">
                                <button class="btn btn-link btn-block text-left collapsed" type="button" data-toggle="collapse" data-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                    Reporte #5
                                </button>
                            </h2>
                        </div>
                        <div id="collapseFive" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
                            <div class="card-body">
                                Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </section>

        <%@include file="js.html" %>
        <script>
            window.onload = function () {
            <c:if test="${admin1 != null}">
                $('#admin1').click();
                $('#date1').val('${date1}');
                $('#date2').val('${date2}');
            </c:if>

            <c:if test="${admin2 != null}">
                $('#admin2').click();
                $('#date3').val('${date3}');
                $('#date4').val('${date4}');
            </c:if>

            <c:if test="${admin3 != null}">
                $('#admin3').click();
                $('#date5').val('${date5}');
                $('#date6').val('${date6}');
            </c:if>

            };
        </script>
    </body>
</html>
