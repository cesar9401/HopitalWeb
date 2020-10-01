
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
        
        <h1>Hello World!</h1>
        
        <%@include file="js.html" %>
    </body>
</html>
