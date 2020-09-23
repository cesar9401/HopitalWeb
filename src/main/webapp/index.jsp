<%-- 
    Document   : index
    Created on : Sep 22, 2020, 4:38:40 PM
    Author     : cesar31
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hospital Pasteur</title>
        <link rel="icon" type="image/ico" href="resources/hospital.ico">
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
                        <input type="email" id="email" class="input" name="email" placeholder="Email">
                        <br />
                        <input type="password" name="pass" id="pass" class="input" name="pass" placeholder="Contraseña">
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
        <script src="js/main.js"></script>
    </body>
</html>
