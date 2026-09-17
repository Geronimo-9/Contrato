<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ page import="org.contrato.model.domain.entity.Usuario" %>
<%
    Usuario listado[] = (Usuario[]) session.getAttribute("usuario.listar");
    String mensaje = null;
    if (listado == null || listado.length <= 0) {
        mensaje = "Resultado: 0 Usuarios encontrados en el Sistema";
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar Usuarios</title>
    </head>
    <body>
    <center>
        <h1>Todos los Usuarios Agregados al Sistema</h1>
        <%
            if (mensaje != null) {
                out.print("<p style='color:red;'>" + mensaje + "</p>");
            } else {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Item</th>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Rol</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int contador = 0;
                    for (Usuario alguien : listado) {
                        contador = contador + 1;
                %>
                <tr>
                    <td><%= contador%></td>
                    <td><%= alguien.getId().valores()%></td>
                    <td><%= alguien.getNombre().valores()%></td>
                    <td><%= alguien.getRol()%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
            }
        %>
        <hr>
        <a href="../../index.jsp"><<= VOLVER_AL_MENU</a>
    </center>
</body>
</html>