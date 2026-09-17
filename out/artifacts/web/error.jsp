<%@ page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error en la Operación</title>
    </head>
    <body>
    <center>
        <h1 style="color:red;">¡Ha ocurrido un error de validación!</h1>
        <hr/>
        <p style="color:#FF0000; font-size: 16px; font-weight: bold;">
            <%= (exception != null) ? exception.getMessage() : "Error desconocido en el sistema." %>
        </p>
        <br/>
        <a href="javascript:history.back()"><< Volver a intentar</a>
    </center>
</body>
</html>