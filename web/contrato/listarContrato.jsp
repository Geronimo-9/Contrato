<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.contrato.model.domain.entity.Usuario"%>
<%@page import="org.contrato.model.domain.entity.Contrato"%>
<%
    if (session.getAttribute("usuario.login") == null) {
        response.sendRedirect("../../index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Contratos</title>
</head>
<body>
    <h2>Todos los Contratos en el Sistema</h2>
    <a href="contrato.jsp">← Volver al menú de contratos</a>
    <br><br>

    <table border="1" cellpadding="8">
        <tr>
            <th>ID Usuario</th>
            <th>Empresa</th>
            <th>Empleado</th>
            <th>Monto</th>
            <th>Frecuencia</th>
            <th>Fecha Inicio</th>
            <th>Fecha Fin</th>
            <th>Estado</th>
        </tr>
        <%
            Contrato[] listado = (Contrato[]) session.getAttribute("contrato.listar");
            if (listado != null) {
                for (Contrato c : listado) {
        %>
        <tr>
            <td><%= c.getIdUsuario().valores() %></td>
            <td><%= c.getEmpresa().valores() %></td>
            <td><%= c.getEmpleado().valores() %></td>
            <td><%= c.getMonto().getMonto() %></td>
            <td><%= c.getFrecuenciaPago() %></td>
            <td><%= c.getFechaInicio().fecha() %></td>
            <td><%= c.getFechaFin().fecha() %></td>
            <td><%= c.getEstado() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="8">No hay contratos para mostrar. Ve al menú y selecciona "Listar Todos los Contratos".</td>
        </tr>
        <% } %>
    </table>
</body>
</html>