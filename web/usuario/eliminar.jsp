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
    <title>Eliminar Usuario</title>
</head>
<body>
<center>
    <h1>Eliminar Usuario</h1>
    <hr/>
    <!-- Primero busca el usuario pasando la redirección a borrar -->
    <form action="<%= request.getContextPath() %>/usuario?accion=buscar&redir=borrar" method="post">
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
    <!-- Formulario para confirmar la eliminación -->
    <form action="<%= request.getContextPath() %>/usuario?accion=borrar" method="post">
        <input type="hidden" name="id" value="<%= alguien.getId().valores() %>">
        <table>
            <tr>
                <th style="text-align: right">ID Encontrado:</th>
                <td style="text-align: left"><%= alguien.getId().valores() %></td>
            </tr>
            <tr>
                <th style="text-align: right">Nombre:</th>
                <td style="text-align: left"><%= (alguien.getNombre() != null) ? alguien.getNombre().valores() : "" %></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center;"><input type="submit" value="Confirmar Eliminación"/></td>
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