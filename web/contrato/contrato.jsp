<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.contrato.model.domain.entity.Usuario"%>
<%
    // Seguridad: Si escriben la URL directo sin loguearse, los devuelve al index
    Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario.login");
    if (usuarioLogueado == null) {
        response.sendRedirect("../../index.jsp?mensaje=Debe iniciar sesion primero");
        return;
    }
    String mensaje = request.getParameter("mensaje");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Módulo de Contratos</title>
</head>
<body>
    <center>
        <h1>Gestión de Contratos</h1>
        <p>Usuario activo: <strong><%= usuarioLogueado.getNombre().valores() %></strong></p>

        <% if (mensaje != null) { %>
            <p style="color: green; font-weight: bold;"><%= mensaje %></p>
        <% } %>
        <hr/>

        <h3>Opciones de Contrato</h3>
        <table border="0">
            <tr><td><a href="agregarContrato.jsp">1. Agregar un Nuevo Contrato</a></td></tr>
            <!-- Recuerda: Estos enlaces van al Servlet primero para consultar la BD -->
            <tr><td><a href="../../contrato?accion=buscar&redir=buscar">2. Consultar mis Contratos</a></td></tr>
            <tr><td><a href="../../contrato?accion=buscar&redir=modificar">3. Modificar mi Contrato</a></td></tr>
            <tr><td><a href="../../contrato?accion=buscar&redir=borrar">4. Eliminar mi Contrato</a></td></tr>
            <tr><td><a href="../../contrato?accion=listartodo">5. Listar Todos los Contratos (Admin)</a></td></tr>
        </table>

        <hr/>
        <!-- Botón para retroceder al index -->
        <a href="../../index.jsp">⬅ Volver al menú principal</a>
    </center>
</body>
</html>