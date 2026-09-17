<%@page import="org.contrato.model.domain.entity.Usuario"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("usuario.login") == null) {
        request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        return;
    }
    String mensaje = request.getParameter("mensaje");
    Usuario alguien = (Usuario) request.getSession().getAttribute("usuario.buscar");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Modificar Usuario</title>
</head>
<body>
<center>
    <h1>Modificar Usuario</h1>
    <hr/>
    <!-- Busca al usuario antes de modificar -->
    <form action="<%= request.getContextPath() %>/usuario?accion=buscar&redir=modificar" method="post">
        <table>
            <tr>
                <th style="text-align: right">ID:</th>
                <td><input type="text" name="id"/></td>
            </tr>
            <tr>
                <th><input type="submit" value="Buscar"></th>
                <td><input type="reset" name="Limpiar"/></td>
            </tr>
        </table>
    </form>
    <hr/>
    <%
        if (alguien != null) {
    %>
    <!-- Formulario con la etiqueta <form> limpia y corregida -->
    <form action="<%= request.getContextPath() %>/usuario?accion=modificar" method="post">
        <table>
            <tr>
                <th style="text-align: right">ID:</th>
                <td><input type="text" name="id" value="<%= (alguien != null && alguien.getId() != null) ? alguien.getId().valores() : "" %>" readonly="readonly"/></td>
            </tr>
            <tr>
                <th style="text-align: right">Password:</th>
                <td><input type="password" name="contraseña" value="<%= (alguien != null && alguien.getContrasena() != null) ? alguien.getContrasena().valores() : "" %>"/></td>
            </tr>
            <tr>
                <th style="text-align: right">Nombre:</th>
                <td><input type="text" name="nombre" value="<%= (alguien != null && alguien.getNombre() != null) ? alguien.getNombre().valores() : "" %>"/></td>
            </tr>
            <tr>
                <th style="text-align: right">Rol:</th>
                <th>
                    <select name="rol">
                        <option value="EMPRESA" <%= (alguien != null && alguien.getRol() != null && alguien.getRol().name().equals("EMPRESA")) ? "selected" : "" %>>EMPRESA</option>
                        <option value="USUARIO" <%= (alguien != null && alguien.getRol() != null && alguien.getRol().name().equals("USUARIO")) ? "selected" : "" %>>USUARIO</option>
                        <option value="PENDIENTE" <%= (alguien != null && alguien.getRol() != null && alguien.getRol().name().equals("PENDIENTE")) ? "selected" : "" %>>PENDIENTE</option>
                    </select>
                </th>
            </tr>
            <tr>
                <th><input type="submit" value="Modificar"></th>
                <td><input type="reset" name="Limpiar"/></td>
            </tr>
        </table>
    </form>
    <%
        }
    %>
    <p style="color:#FF0000;">
        <%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : ""%>
    </p>
    <% request.getSession().setAttribute("usuario.buscar", null);%>
</center>
</body>
</html>