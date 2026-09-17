<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.contrato.model.domain.entity.Contrato" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buscar y Filtrar Contratos</title>
</head>
<body>
<h1>Consulta de Contratos</h1>

<!-- Mensajes de éxito o error -->
<% String mensaje = request.getParameter("mensaje");
    if (mensaje != null) { %>
<p style="color: green; font-weight: bold;"><%= mensaje %></p>
<% } %>

<!-- FORMULARIO DE FILTROS -->
<form action="${pageContext.request.contextPath}/contrato" method="GET">
    <input type="hidden" name="accion" value="buscar">

    <label>Empresa:</label>
    <input type="text" name="paramEmpresa" value="<%= request.getParameter("paramEmpresa") != null ? request.getParameter("paramEmpresa") : "" %>">

    <label>Estado:</label>
    <select name="paramEstado">
        <option value="TODOS">TODOS</option>
        <option value="VIGENTE">VIGENTE</option>
        <option value="VENCIDO">VENCIDO</option>
    </select>

    <button type="submit">Filtrar</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/index.jsp">Volver al inicio</a>
<hr>

<!-- TABLA DE RESULTADOS -->
<h3>Resultados:</h3>
<table border="1">
    <tr>
        <th>Empresa</th>
        <th>Empleado</th>
        <th>Monto</th>
        <th>Frecuencia Pago</th>
        <th>Fecha Firma</th>
        <th>Fecha Inicio</th>
        <th>Fecha Fin</th>
        <th>Estado</th>
    </tr>
        <%
            Contrato[] contratos = (Contrato[]) session.getAttribute("contrato.buscar");
            if (contratos != null && contratos.length > 0) {
                for (Contrato c : contratos) {
        %>
    <tr>
        <td><%= c.getEmpresa().valores() %></td>
        <td><%= c.getEmpleado().valores() %></td>
        <td><%= c.getMonto().getMonto() %></td>
        <td><%= c.getFrecuenciaPago() %></td>
        <td><%= c.getFechaFirma().fecha() %></td>
        <td><%= c.getFechaInicio().fecha() %></td>
        <td><%= c.getFechaFin().fecha() %></td>
        <td><%= c.getEstado() %></td>
    </tr>
        <%
                }
            } else {
        %>
    <tr>
        <td colspan="8" style="text-align: center;">No hay contratos para mostrar. Realiza una búsqueda.</td>
    </tr>
        <% } %>
    </ul>
</table>
</body>
</html>