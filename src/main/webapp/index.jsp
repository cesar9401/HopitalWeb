<%-- 
    Document   : index
    Created on : Sep 22, 2020, 4:38:40 PM
    Author     : cesar31
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hospital Pasteur</title>
        <%@include file="css.html" %>
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>
        <main>
            <section class="contenedor">
                <div class="izquierda">
                    <h1 class="hospital">HOSPITAL</h1>
                    <h1 class="pasteur">PASTEUR</h1>
                    <img src="resources/louis-pasteur.png" alt="Pasteur" width="200px">
                </div>
                <div class="derecha">
                    <form action="MainController" method="post" class="formulario" id="formulario" name="form1" onsubmit="return validarFormulario(this)">
                        <input type="email" id="email" class="input" name="email" placeholder="Email" value="mailAFZ@hospital.com" required>
                        <br />
                        <input type="password" name="pass" id="pass" class="input" name="pass" placeholder="Contraseña" value="C9^r:F/j" required>
                        <br />
                        <select name="tipoUsuario" class="input lista" id="lista">
                            <option value="PATIENTS">Paciente</option>
                            <option value="DOCTORS">Doctor</option>
                            <option value="LAB_WORKERS">Laboratorista</option>
                            <option value="ADMINISTRATORS">Administrador</option>
                        </select>
                        <br />
                        <input type="submit" name="action" value="login" class="boton">
                    </form>
                </div>
            </section>
        </main>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel"><span class="badge badge-danger">Error</span></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Su usuario y/o contraseña son incorrectos</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="js.html" %>
        <script src="js/main.js"></script>
        <c:if test="${error != null}">
            <script type="text/javascript">
                        $(document).ready(function () {
                            $('#exampleModal').modal('show');
                        });
            </script>        
        </c:if>
    </body>
</html>
