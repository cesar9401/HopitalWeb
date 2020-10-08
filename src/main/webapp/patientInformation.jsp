
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css.html" %>
        <title>Hospital Pasteur</title>
    </head>
    <body>
        <!--Formulario Aqui-->
        <section id="mainDoc">
            <div class="container my-4">
                <div class="row">
                    <div class="col text-center">
                        <h1 class="text-info" id="title">Registrarme como Paciente</h1>
                    </div>
                </div>

                <form action="MainController" method="post">
                    <div class="form-row">
                        <div class="form-group col-md-3">
                            <label for="id">Id</label>
                            <input type="text" class="form-control" id="doctorId" name="patientId" readonly>
                        </div>
                        <div class="form-group col-md-5">
                            <label for="name">Nombre</label>
                            <input type="text" class="form-control" id="name" name="name" placeholder="Nombre" required>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="phone">Telefono</label>
                            <input type="number" min="0" class="form-control" id="phone" name="phone" placeholder="22221111" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="dpi">DPI</label>
                            <input type="number" min="0" class="form-control" id="dpi" name="dpi" placeholder="DPI" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="gender">Genero</label>
                            <select class="custom-select" name="gender" id="gender" required>
                                <option value="" selected>Choose...</option>
                                <option value="1">Masculino</option>
                                <option value="0">Femenino</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="date">Fecha de Nacimiento</label>
                            <input type="date" id="date" name="date" class="form-control" required>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="weight">Peso</label>
                            <input type="number" name="weight" step="any" min="0" class="form-control" placeholder="Peso en kg" required>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="blood">Tipo de Sangre</label>
                            <select class="custom-select" name="blood" id="blood" required>
                                <option value="" selected>Choose...</option>
                                <option value="O-">O-</option>
                                <option value="O+">O+</option>
                                <option value="A-">A-</option>
                                <option value="A+">A+</option>
                                <option value="B-">B-</option>
                                <option value="B+">B+</option>
                                <option value="AB-">AB-</option>
                                <option value="AB+">AB+</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="inputEmail">Email</label>
                            <input type="email" class="form-control" id="email" name="email" placeholder="example@email.com" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="pass" name="pass" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-2 offset-md-5">
                            <button type="submit" name="action" value="addPatient" id="btn-submit" class="btn btn-primary btn-block">Registrarme</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>

        <%@include file="js.html" %>
    </body>
</html>
