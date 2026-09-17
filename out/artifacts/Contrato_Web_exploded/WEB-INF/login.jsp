<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String mensaje = request.getParameter("mensaje");
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Iniciar Sesión</title>
</head>

<body>
<center>

    <h1>Login de Usuario</h1>
    <hr/>

    <form action="<%= request.getContextPath() %>/usuario?accion=login" method="post">

        <table>
            <tr>
                <th style="text-align: right">ID:</th>
                <td>
                    <input type="text" name="id"/>
                </td>
            </tr>

            <tr>
                <th style="text-align: right">Password:</th>
                <td>
                    <input type="password" name="contraseña"/>
                </td>
            </tr>

            <tr>
                <th>
                    <input type="submit" value="Entrar">
                </th>

                <td>
                    <input type="reset" value="Limpiar"/>
                </td>
            </tr>
        </table>

    </form>

    <hr/>

    <p style="color:#FF0000;">
        <%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : ""%>
    </p>

</center>
</body>
</html>