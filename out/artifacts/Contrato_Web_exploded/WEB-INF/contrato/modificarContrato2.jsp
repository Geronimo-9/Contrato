<%@page import="org.contrato.model.domain.entity.Contrato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Contrato[] contratos = (Contrato[]) request.getSession().getAttribute("contrato.buscar");
    String indiceStr = request.getParameter("indiceContrato");
    Contrato c = null;
    if (contratos != null && indiceStr != null) {
        try { c = contratos[Integer.parseInt(indiceStr)]; } catch (Exception e) {}
    }
    if (c == null) {
        response.sendRedirect("modificarContrato.jsp?mensaje=Por favor seleccione un contrato.");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Actualizar Contrato</title>
</head>
<body>
<center>
    <h1>Modificar Contrato</h1>
    <hr style="width: 100%;">

    <form action="<%= request.getContextPath() %>/contrato" method="post">
        <input type="hidden" name="accion" value="modificar">
        <input type="hidden" name="empresaOriginal" value="<%= c.getEmpresa().valores() %>">
        <table cellpadding="5" cellspacing="0" style="text-align: left;">
            <tr><td><b>Empresa:</b></td><td><input type="text" name="empresa" value="<%= c.getEmpresa().valores() %>" required></td></tr>
            <tr><td><b>Empleado:</b></td><td><input type="text" name="empleado" value="<%= c.getEmpleado().valores() %>" required></td></tr>
            <tr><td><b>Funciones:</b></td><td><textarea name="funciones" rows="3" required><%= c.getFunciones().texto() %></textarea></td></tr>
            <tr><td><b>Monto:</b></td><td><input type="number" step="0.01" name="monto" value="<%= c.getMonto().getMonto() %>" required></td></tr>
            <tr>
                <td><b>Frecuencia de Pago:</b></td>
                <td>
                    <select name="frecuenciaPago" required>
                        <option value="<%= c.getFrecuenciaPago() %>" selected>Actual: <%= c.getFrecuenciaPago() %></option>
                        <option value="MENSUAL">Mensual</option>
                        <option value="QUINCENAL">Quincenal</option>
                        <option value="SEMANAL">Semanal</option>
                    </select>
                </td>
            </tr>
            <tr><td><b>Fecha de Firma:</b></td><td><input type="date" name="fechafirma" value="<%= c.getFechaFirma().fecha() %>" required></td></tr>
            <tr><td><b>Fecha de Inicio:</b></td><td><input type="date" name="fechaIncio" value="<%= c.getFechaInicio().fecha() %>" required></td></tr>
            <tr><td><b>Fecha de Fin:</b></td><td><input type="date" name="fechaFin" value="<%= c.getFechaFin().fecha() %>" required></td></tr>
            <tr>
                <td><b>Estado:</b></td>
                <td>
                    <select name="estado" required>
                        <option value="<%= c.getEstado() %>" selected>Actual: <%= c.getEstado() %></option>
                        <option value="VIGENTE">Vigente</option>
                        <option value="VENCIDO">Vencido</option>
                        <option value="CANCELADO">Cancelado</option>
                    </select>
                </td>
            </tr>
        </table>
        <br>
        <input type="submit" value="Guardar Cambios">
        <input type="button" value="Cancelar" onclick="window.location.href='modificarContrato.jsp';">
    </form>
</center>
</body>
</html>