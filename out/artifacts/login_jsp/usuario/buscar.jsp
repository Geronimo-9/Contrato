<%@page import="org.contrato.model.domain.entity.Usuario"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("usuario.login") == null) {
        request.getServletContext().getRequestDispatcher("/web/usuario/login.jsp").forward(request, response);
    return;
    }
    String mensaje = request.getParameter("mensaje");
    Usuario alguien = (Usuario) request.getSession().getAttribute("usuario.buscar");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultar Usuario</title>
    </head>
    <body>
    <center>
        <h1>Buscar Usuario</h1>
        <hr/>

        <form action="<%= request.getContextPath() %>/usuario" method="get">

        <input type="hidden" name="accion" value="buscar"/>
            <table>
                <tr>
                    <th style="text-align: right">ID:</th>
                    <td><input type="text" name="id"/></td>
                </tr>
                <tr>
                    <th><input type="submit" value="Buscar"></th>
                    <td><input type="reset" name="Limpiar"/></td>
                </tr>
                <tr>
                    <th style="text-align: right">Password:</th>
                    <td style="text-align: left"><%= (alguien != null) ? "********" : "" %></td>
                </tr>
                <tr>
                    <th style="text-align: right">Nombre:</th>
                    <td style="text-align: left"><%= (alguien != null && alguien.getNombre() != null) ? alguien.getNombre().valores() : "" %></td>
                </tr>
                <tr>
                    <th style="text-align: right">Rol:</th>
                    <td style="text-align: left"><%= (alguien != null && alguien.getRol() != null) ? alguien.getRol() : "" %></td>
                </tr>
            </table>
        </form>
        <hr/>
        <p style="color:#FF0000;">
            <%= (mensaje != null && !mensaje.isEmpty()) ? mensaje : ""%>
        </p>
        <% request.getSession().setAttribute("usuario.buscar", null);%>
    </center>
</body>
</html>