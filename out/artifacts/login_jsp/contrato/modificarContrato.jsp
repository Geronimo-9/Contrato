<%@page import="org.contrato.model.domain.entity.Contrato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Contrato[] contratos = (Contrato[]) request.getSession().getAttribute("contrato.buscar");
    String mensaje = request.getParameter("mensaje");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modificar Contrato</title>
</head>
<body>
<center>
    <h1>Seleccione el Contrato a Modificar</h1>
    <hr style="width: 100%;">


    <form action="<%= request.getContextPath() %>/contrato" method="post" style="margin-bottom: 20px; border: 1px solid #ccc; padding: 10px; display: inline-block;">
        <input type="hidden" name="accion" value="buscar">
        <input type="hidden" name="redir" value="modificar">

        <b>Filtros de Reporte:</b>&nbsp;
        <label>Empresa:</label>
        <input type="text" name="paramEmpresa" placeholder="Buscar empresa...">&nbsp;

        <label>Estado:</label>
        <select name="paramEstado">
            <option value="TODOS">Todos</option>
            <option value="VIGENTE">Vigente</option>
            <option value="VENCIDO">Vencido</option>
            <option value="CANCELADO">Cancelado</option>
        </select>&nbsp;

        <input type="submit" value="Buscar mis contratos">
    </form>

    <p style="color: blue;"><%= (mensaje != null) ? mensaje : "" %></p>

    <% if (contratos != null && contratos.length > 0) { %>
    <form action="modificarContrato2.jsp" method="post">
        <table border="1" cellpadding="8" cellspacing="0" style="text-align: center;">
            <tr style="background-color: #f2f2f2;">
                <th>Seleccionar</th>
                <th>Empresa</th>
                <th>Empleado</th>
                <th>Estado</th>
                <th>Monto</th>
            </tr>
            <% for (int i = 0; i < contratos.length; i++) { %>
            <tr>
                <td><input type="radio" name="indiceContrato" value="<%= i %>" required></td>
                <td><%= contratos[i].getEmpresa().valores() %></td>
                <td><%= contratos[i].getEmpleado().valores() %></td>
                <td><%= contratos[i].getEstado() %></td>
                <td><%= contratos[i].getMonto().getMonto() %></td>
            </tr>
            <% } %>
        </table>
        <br>
        <input type="submit" value="Ir a Modificar Contrato">
    </form>
    <% } %>
</center>
</body>
</html>