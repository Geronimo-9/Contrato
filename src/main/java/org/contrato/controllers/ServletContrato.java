package org.contrato.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.contrato.model.domain.entity.Contrato;
import org.contrato.model.domain.entity.Usuario;
import org.contrato.model.domain.valueobject.contrato.*;
import org.contrato.model.domain.valueobject.usuario.IdUsuario;
import org.contrato.model.persistence.CRUDContrato;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet(name = "ServletContrato", urlPatterns = {"/contrato"})
public class ServletContrato extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "";
        }

        String rutaBase = request.getContextPath() + "/contrato/";
        String rutaIndex = request.getContextPath() + "/index.jsp";

        try {
            // 1. Validación de Sesión y obtención de llave foránea (FK)
            Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario.login");
            if (usuarioLogueado == null) {
                throw new Exception("Acceso denegado: Debe iniciar sesión.");
            }
            IdUsuario idUsuarioActivo = usuarioLogueado.getId();


            if (accion.equals("agregar")) {
                CRUDContrato crud = new CRUDContrato();

                crud.getContrato().setIdUsuario(idUsuarioActivo);
                crud.getContrato().setEmpresa(new Empresa(request.getParameter("empresa")));
                crud.getContrato().setEmpleado(new Empleado(request.getParameter("empleado")));
                crud.getContrato().setFunciones(new Funciones(request.getParameter("funciones")));
                crud.getContrato().setMonto(new Monto(Double.parseDouble(request.getParameter("monto"))));
                crud.getContrato().setFrecuenciaPago(FrecuenciaPago.valueOf(request.getParameter("frecuenciaPago")));
                crud.getContrato().setFechaFirma(new FechaFirma(LocalDate.parse(request.getParameter("fechafirma"))));
                crud.getContrato().setFechaInicio(new FechaInicio(LocalDate.parse(request.getParameter("fechaIncio"))));
                crud.getContrato().setFechaFin(new FechaFin(LocalDate.parse(request.getParameter("fechaFin"))));
                crud.getContrato().setEstado(Estado.valueOf(request.getParameter("estado")));

                crud.agregarContrato();

                Contrato[] listaCompleta = CRUDContrato.consultarContratosFiltrados(idUsuarioActivo.valores(), null, null);
                request.getSession().setAttribute("contrato.buscar", listaCompleta);

                response.sendRedirect(rutaBase + "agregarContrato.jsp?mensaje=Contrato agregado exitosamente");

            } else if (accion.equals("buscar")) {
                // Parámetros de los reportes enviados desde el JSP
                String paramEmpresa = request.getParameter("paramEmpresa");
                String paramEstado = request.getParameter("paramEstado");

                // Llamada al nuevo método del CRUD que maneja la base de datos y los filtros
                Contrato[] filtrados = CRUDContrato.consultarContratosFiltrados(idUsuarioActivo.valores(), paramEmpresa, paramEstado);

                request.getSession().setAttribute("contrato.buscar", filtrados);

                // Lógica de redirección dinámica
                String redir = request.getParameter("redir");
                if ("borrar".equals(redir)) {
                    response.sendRedirect(rutaBase + "eliminarContrato.jsp");
                } else if ("modificar".equals(redir)) {
                    response.sendRedirect(rutaBase + "modificarContrato.jsp");
                } else {
                    response.sendRedirect(rutaBase + "buscarContrato.jsp");
                }


            } else if (accion.equals("modificar")) {
                String empresaOriginal = request.getParameter("empresaOriginal"); // Capturamos la original

                CRUDContrato crud = new CRUDContrato();
                crud.getContrato().setIdUsuario(idUsuarioActivo);
                crud.getContrato().setEmpresa(new Empresa(request.getParameter("empresa")));
                crud.getContrato().setEmpleado(new Empleado(request.getParameter("empleado")));
                crud.getContrato().setFunciones(new Funciones(request.getParameter("funciones")));
                crud.getContrato().setMonto(new Monto(Double.parseDouble(request.getParameter("monto"))));
                crud.getContrato().setFrecuenciaPago(FrecuenciaPago.valueOf(request.getParameter("frecuenciaPago")));
                crud.getContrato().setFechaFirma(new FechaFirma(LocalDate.parse(request.getParameter("fechafirma"))));
                crud.getContrato().setFechaInicio(new FechaInicio(LocalDate.parse(request.getParameter("fechaIncio"))));
                crud.getContrato().setFechaFin(new FechaFin(LocalDate.parse(request.getParameter("fechaFin"))));
                crud.getContrato().setEstado(Estado.valueOf(request.getParameter("estado")));


                crud.modificarContrato(empresaOriginal);


                Contrato[] actualizados = CRUDContrato.consultarContratosFiltrados(idUsuarioActivo.valores(), null, null);
                request.getSession().setAttribute("contrato.buscar", actualizados);

                response.sendRedirect(rutaBase + "modificarContrato.jsp?mensaje=Contrato modificado exitosamente");


            } else if (accion.equals("borrar")) {

                String indiceStr = request.getParameter("indiceContrato");
                Contrato[] contratosEnSesion = (Contrato[]) request.getSession().getAttribute("contrato.buscar");
                Contrato contratoSeleccionado = contratosEnSesion[Integer.parseInt(indiceStr)];
                String empresaSeleccionada = contratoSeleccionado.getEmpresa().valores();

                CRUDContrato crud = new CRUDContrato();
                crud.getContrato().setIdUsuario(idUsuarioActivo);


                crud.eliminarContrato(empresaSeleccionada);


                Contrato[] actualizados = CRUDContrato.consultarContratosFiltrados(idUsuarioActivo.valores(), null, null);
                request.getSession().setAttribute("contrato.buscar", actualizados);

                response.sendRedirect(rutaBase + "eliminarContrato.jsp?mensaje=Contrato eliminado exitosamente");


            } else if (accion.equals("listartodo")) {

                request.getSession().removeAttribute("contrato.buscar");
                Contrato[] listaCompleta = CRUDContrato.consultarContratosFiltrados(idUsuarioActivo.valores(), null, null);
                request.getSession().setAttribute("contrato.buscar", listaCompleta);
                response.sendRedirect(rutaBase + "buscarContrato.jsp");

            } else {
                response.sendRedirect(rutaIndex + "?mensaje=Acción no válida en contratos");
            }

        } catch (Exception error) {
            String rutaError = request.getContextPath() + "/contrato/contrato.jsp?mensaje=" + error.getMessage();
            response.sendRedirect(rutaError);
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException { processRequest(request, res); }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException { processRequest(request, res); }
}