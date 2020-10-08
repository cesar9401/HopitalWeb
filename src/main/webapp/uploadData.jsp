<%-- 
    Document   : cargarDatos
    Created on : Sep 21, 2020, 6:22:50 AM
    Author     : cesar31
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css.html"%>
        <title>Hospital Web</title>
    </head>
    <body>
        <div class="container">
            <div class="row mt-4 mb-2">
                <div class="col-12">
                    <h1 class="text-center">Carga de Datos</h1>
                </div>
            </div>
            <div class="row mt-4 mb-4">
                <div class="col-12 col-md-4 offset-md-4">
                    <form action="MainController" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="exampleFormControlFile1">Example file input</label>
                            <input type="file" class="form-control-file text-center" id="exampleFormControlFile1" accept=".xml" name="file">
                        </div>
                        <button type="submit" class="btn btn-primary btn-block" name="action" value="load">Send</button>
                    </form>                   
                </div>
            </div>
        </div>
        <%@include file="js.html"%>
    </body>
</html>
                                                                                