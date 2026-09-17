package org.contrato.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.contrato.model.domain.valueobject.usuario.*;
import org.contrato.model.persistence.CRUDUsuario;
import org.contrato.model.domain.entity.Usuario;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletUsuario", urlPatterns = {"/usuario"})
public class ServletUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "";
        }


        String rutaBase = request.getContextPath() + "/usuario/";
        String rutaIndex = request.getContextPath() + "/index.jsp";
        String rutaLogin = request.getContextPath() + "/login.jsp";

        try {
            if (accion.equals("agregar")) {
                CRUDUsuario crud = new CRUDUsuario();
                crud.getUsuario().setId(new IdUsuario(request.getParameter("id")));
                crud.getUsuario().setNombre(new NombreUsuario(request.getParameter("nombre")));
                crud.getUsuario().setContrasena(new ContrasenaUsuario(request.getParameter("contraseña")));
                crud.getUsuario().setRol(RolUsuario.valueOf(request.getParameter("rol")));
                crud.agregarUsuario();
                response.sendRedirect(rutaBase + "agregar.jsp?mensaje=Usuario Agregado exitosamente");

            } else if (accion.equals("buscar")) {
                Usuario usuario = CRUDUsuario.consultarUsuario(new IdUsuario(request.getParameter("id")));
                request.getSession().setAttribute("usuario.buscar", usuario);

                String redir = request.getParameter("redir");
                if ("borrar".equals(redir)) {
                    response.sendRedirect(rutaBase + "eliminar.jsp");
                } else if ("modificar".equals(redir)) {
                    response.sendRedirect(rutaBase + "modificar.jsp");
                } else {
                    response.sendRedirect(rutaBase + "buscar.jsp");
                }

            } else if (accion.equals("modificar")) {
                CRUDUsuario crud = new CRUDUsuario();
                crud.getUsuario().setId(new IdUsuario(request.getParameter("id")));
                crud.getUsuario().setNombre(new NombreUsuario(request.getParameter("nombre")));
                crud.getUsuario().setContrasena(new ContrasenaUsuario(request.getParameter("contraseña")));
                crud.getUsuario().setRol(RolUsuario.valueOf(request.getParameter("rol")));
                crud.modificarUsuario();
                response.sendRedirect(rutaBase + "modificar.jsp?mensaje=Usuario Modificado exitosamente");

            } else if (accion.equals("borrar")) {
                CRUDUsuario crud = new CRUDUsuario();
                crud.getUsuario().setId(new IdUsuario(request.getParameter("id")));
                crud.eliminarUsuario();
                response.sendRedirect(rutaBase + "eliminar.jsp?mensaje=Usuario Eliminado del sistema");

            } else if (accion.equals("listartodo")) {
                Usuario[] listado = CRUDUsuario.listarUsuarios();
                request.getSession().setAttribute("usuario.listar", listado);
                response.sendRedirect(rutaBase + "listar.jsp");

            } else if (accion.equals("login")) {
                Usuario usuario = CRUDUsuario.iniciarSesion(new IdUsuario(request.getParameter("id")), new ContrasenaUsuario(request.getParameter("contraseña")));
                request.getSession().setAttribute("usuario.login", usuario);
                response.sendRedirect(rutaIndex + "?mensaje=Bienvenido al Sistema");

            } else if (accion.equals("salir")) {
                request.getSession().setAttribute("usuario.login", null);
                request.getSession().invalidate();
                response.sendRedirect(rutaLogin + "?mensaje=Sesion Cerrada Exitosamente");

            } else {
                response.sendRedirect(rutaIndex + "?mensaje=Accion Solicitada no es Correcta");
            }

        } catch (Exception error) {
            if (accion.equals("login")) {
                response.sendRedirect(rutaLogin + "?mensaje=" + error.getMessage());
            } else {
                response.sendRedirect(rutaIndex + "?mensaje=" + error.getMessage());
            }
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException { processRequest(request, res); }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException { processRequest(request, res); }
}