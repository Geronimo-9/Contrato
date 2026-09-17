<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.contrato.model.domain.entity.Usuario"%>
<%
    if (session.getAttribute("usuario.login") == null) {
        response.sendRedirect("../../index.jsp");
        return;
    }
    String mensaje = request.getParameter("mensaje");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Contrato</title>
</head>
<body>
    <h2>Registrar Nuevo Contrato</h2>
    <a href="contrato.jsp">← Volver al menú de contratos</a>
    <br><br>

    <% if (mensaje != null) { %><p style="color: blue;"><%= mensaje %></p><% } %>

    <form action="<%= request.getContextPath() %>/contrato?accion=agregar" method="post">
        <input type="hidden" name="accion" value="agregar">

        <label>Empresa:</label><br>
        <input type="text" name="empresa" required><br><br>

        <label>Empleado:</label><br>
        <input type="text" name="empleado" required><br><br>

        <label>Funciones:</label><br>
        <textarea name="funciones" rows="4" required></textarea><br><br>

        <label>Monto:</label><br>
        <input type="number" step="0.01" name="monto" required><br><br>

        <label>Frecuencia de Pago:</label><br>
        <select name="frecuenciaPago" required>
            <option value="MENSUAL">Mensual</option>
            <option value="QUINCENAL">Quincenal</option>
            <option value="SEMANAL">Semanal</option>
        </select><br><br>

        <label>Fecha de Firma:</label><br>
        <input type="date" name="fechafirma" required><br><br>

        <label>Fecha de Inicio:</label><br>
        <!-- Respetando el nombre de variable fechaIncio de tu base de datos y servlet -->
        <input type="date" name="fechaIncio" required><br><br>

        <label>Fecha de Fin:</label><br>
        <input type="date" name="fechaFin" required><br><br>

        <label>Estado:</label><br>
        <select name="estado" required>
            <option value="VIGENTE">Vigente</option>
            <option value="VENCIDO">Vencido</option>
            <option value="CANCELADO">Cancelado</option>
        </select><br><br>

        <input type="submit" value="Guardar Contrato">
    </form>
</body>
</html>