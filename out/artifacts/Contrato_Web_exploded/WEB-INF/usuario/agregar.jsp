<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("usuario.login") == null) {
        application.getRequestDispatcher("/web/usuario/login.jsp").forward(request, response);
        return;
    }
    String mensaje = request.getParameter("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Usuario al Sistema</title>
    </head>
    <body>
    <center>
        <h1>Agregar Usuario</h1>
        <hr/>
        <form action="<%= request.getContextPath() %>/usuario?accion=agregar" method="post">
            <table>
                <tr>
                    <th style="text-align: right">ID:</th>
                    <th><input type="text" name="id"/></th>
                </tr>
                <tr>
                    <th style="text-align: right">Password:</th>
                    <th><input type="password" name="contraseña"/></th>
                </tr>
                <tr>
                    <th style="text-align: right">Nombre:</th>
                    <th><input type="text" name="nombre"/></th>
                </tr>
                <tr>
                    <th style="text-align: right">Rol:</th>
                    <th>
                        <select name="rol">
                            <option value="EMPRESA">EMPRESA</option>
                            <option value="USUARIO">USUARIO</option>
                            <option value="PENDIENTE">PENDIENTE</option>
                        </select>
                    </th>
                </tr>
                <tr>
                    <th><input type="submit" value="ENTRAR"></th>
                    <th><input type="reset" name="LIMPIAR"/></th>
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