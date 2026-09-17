<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Valida que exista alguien logueado. Si no, lo manda al login.
    if (request.getSession().getAttribute("usuario.login") == null) {
        response.sendRedirect("web/usuario/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Principal</title>
    </head>
    <body>
    <center>
        <h1>Sistema de Gestión</h1>

        <%

            String mensaje = request.getParameter("mensaje");
            if (mensaje != null) {
        %>
            <p style="color: blue; font-weight: bold;"><%= mensaje %></p>
        <% } %>

        <hr/>
        <h3>Menú de Usuarios</h3>
        <table border="0">
            <tr><td><a href="usuario/agregar.jsp">1. Agregar Usuario</a></td></tr>
            <tr><td><a href="usuario/buscar.jsp">2. Buscar Usuario</a></td></tr>
            <tr><td><a href="usuario/modificar.jsp">3. Modificar Usuario</a></td></tr>
            <tr><td><a href="usuario/eliminar.jsp">4. Eliminar Usuario</a></td></tr>
            <tr><td><a href="usuario?accion=listartodo">5. Listar Todos los Usuarios</a></td></tr>
        </table>

        <hr/>
        <h3>Menú de Contratos</h3>
        <table border="0">
            <tr><td><a href="contrato/contrato.jsp" style="font-weight: bold; color: green;">👉 Realizar / Gestionar Contratos</a></td></tr>
        </table>

        <hr/>

        <a href="usuario?accion=salir" style="color: red;">Cerrar Sesión</a>
    </center>
</body>
</html>