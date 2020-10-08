
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="css.html" %>
        <title>Hospital Pasteur</title>
    </head>
    <body>

        <a href="MainController?action=takeChoice" class="btn btn-primary" id="button-choice" hidden></a>

        <script>
            this.document.getElementById("button-choice").click();
        </script>
        <%@include file="js.html" %>
    </body>
</html>
