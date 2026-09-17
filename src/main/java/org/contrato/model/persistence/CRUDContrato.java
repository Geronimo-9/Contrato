package org.contrato.model.persistence;

import org.contrato.model.domain.entity.Contrato;
import org.contrato.model.domain.valueobject.contrato.*;
import org.contrato.model.domain.valueobject.usuario.IdUsuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;

public class CRUDContrato {

    private Contrato contrato;
    private DataBaseConfig baseDeDatos;

    public CRUDContrato() {
        this.contrato = new Contrato();
        try {
            this.baseDeDatos = new DataBaseConfig();
        } catch (Exception e) {
            System.out.println("Error al instanciar base de datos: " + e.getMessage());
        }
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public void agregarContrato() throws Exception {
        if (contrato == null || contrato.getIdUsuario() == null || contrato.getIdUsuario().valores().isEmpty()) {
            throw new Exception("El ID del usuario es necesario para crear un contrato.");
        }

        String sqlInsert = "INSERT INTO Contrato (fk_usuario, empresa, empleado, funciones, monto, frecuenciaPago, fechafirma, fechaIncio, fechaFin, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement sentenciaSQL = baseDeDatos.crearSentencias(sqlInsert);
            sentenciaSQL.setString(1, contrato.getIdUsuario().valores());
            sentenciaSQL.setString(2, contrato.getEmpresa().valores());
            sentenciaSQL.setString(3, contrato.getEmpleado().valores());
            sentenciaSQL.setString(4, contrato.getFunciones().texto());
            sentenciaSQL.setDouble(5, contrato.getMonto().getMonto());
            sentenciaSQL.setString(6, contrato.getFrecuenciaPago().toString());
            sentenciaSQL.setDate(7, Date.valueOf(contrato.getFechaFirma().fecha()));
            sentenciaSQL.setDate(8, Date.valueOf(contrato.getFechaInicio().fecha()));
            sentenciaSQL.setDate(9, Date.valueOf(contrato.getFechaFin().fecha()));
            sentenciaSQL.setString(10, contrato.getEstado().toString());

            baseDeDatos.actualizar(sentenciaSQL);
        } catch (Exception error) {
            throw new Exception("Error al agregar el Contrato para el usuario " + contrato.getIdUsuario().valores() + " <br/> Explicacion: " + error);
        } finally {
            baseDeDatos.desconectar();
        }
    }
    public void modificarContrato(String empresaOriginal) throws Exception {
        String sqlUpdate = "UPDATE Contrato SET empresa=?, empleado=?, funciones=?, monto=?, frecuenciaPago=?, fechafirma=?, fechaIncio=?, fechaFin=?, estado=? "
                + "WHERE fk_usuario=? AND empresa=?"; // <--- EL TRUCO ESTÁ AQUÍ

        try {
            PreparedStatement sentenciaSQL = baseDeDatos.crearSentencias(sqlUpdate);
            sentenciaSQL.setString(1, contrato.getEmpresa().valores());
            sentenciaSQL.setString(2, contrato.getEmpleado().valores());
            sentenciaSQL.setString(3, contrato.getFunciones().texto());
            sentenciaSQL.setDouble(4, contrato.getMonto().getMonto());
            sentenciaSQL.setString(5, contrato.getFrecuenciaPago().toString());
            sentenciaSQL.setDate(6, Date.valueOf(contrato.getFechaFirma().fecha()));
            sentenciaSQL.setDate(7, Date.valueOf(contrato.getFechaInicio().fecha()));
            sentenciaSQL.setDate(8, Date.valueOf(contrato.getFechaFin().fecha()));
            sentenciaSQL.setString(9, contrato.getEstado().toString());

            // Los del WHERE
            sentenciaSQL.setString(10, contrato.getIdUsuario().valores());
            sentenciaSQL.setString(11, empresaOriginal);

            baseDeDatos.actualizar(sentenciaSQL);
        } catch (Exception error) {
            throw new Exception("Error al actualizar el Contrato: " + error.getMessage());
        } finally {
            baseDeDatos.desconectar();
        }
    }

    public void eliminarContrato(String empresaSeleccionada) throws Exception {
        String sqlDelete = "DELETE FROM Contrato WHERE fk_usuario=? AND empresa=?"; // <--- AQUÍ TAMBIÉN

        try {
            PreparedStatement sentenciaSQL = baseDeDatos.crearSentencias(sqlDelete);
            sentenciaSQL.setString(1, contrato.getIdUsuario().valores());
            sentenciaSQL.setString(2, empresaSeleccionada);
            baseDeDatos.actualizar(sentenciaSQL);
        } catch (Exception error) {
            throw new Exception("Error al eliminar el Contrato: " + error.getMessage());
        } finally {
            baseDeDatos.desconectar();
        }
    }

    // =========================================================================
    // MÉTODO ÚNICO PARA LISTAR Y CONSULTAR/FILTRAR (Reportes Parametrizados)
    // =========================================================================
    public static Contrato[] consultarContratosFiltrados(String idUsuario, String paramEmpresa, String paramEstado) throws Exception {
        if (idUsuario == null || idUsuario.isEmpty()) {
            throw new Exception("El ID del usuario es necesario para la consulta.");
        }

        DataBaseConfig baseDato = null;
        StringBuilder sql = new StringBuilder("SELECT * FROM Contrato WHERE fk_usuario = ?");

        boolean filtraEmpresa = (paramEmpresa != null && !paramEmpresa.trim().isEmpty());
        boolean filtraEstado = (paramEstado != null && !paramEstado.trim().isEmpty() && !paramEstado.equals("TODOS"));

        // Si hay filtros, se añaden dinámicamente a la consulta SQL
        if (filtraEmpresa) {
            sql.append(" AND LOWER(empresa) LIKE LOWER(?)");
        }
        if (filtraEstado) {
            sql.append(" AND estado = ?");
        }

        try {
            baseDato = new DataBaseConfig();
            PreparedStatement sentenciaSQL = baseDato.crearSentencias(sql.toString());

            // 1. Asignar el ID de usuario (obligatorio)
            sentenciaSQL.setString(1, idUsuario);

            // 2. Asignar los parámetros dinámicos si existen
            int index = 2;
            if (filtraEmpresa) {
                sentenciaSQL.setString(index++, "%" + paramEmpresa.trim() + "%");
            }
            if (filtraEstado) {
                sentenciaSQL.setString(index++, paramEstado);
            }

            ResultSet resultado = baseDato.consultar(sentenciaSQL);
            java.util.ArrayList<Contrato> listaContratos = new java.util.ArrayList<>();

            while (resultado.next()) {
                Contrato contratoConsultado = new Contrato(
                        new IdUsuario(resultado.getString("fk_usuario")),
                        new Empresa(resultado.getString("empresa")),
                        new Empleado(resultado.getString("empleado")),
                        new Funciones(resultado.getString("funciones")),
                        new Monto(resultado.getDouble("monto")),
                        FrecuenciaPago.valueOf(resultado.getString("frecuenciaPago")),
                        new FechaFirma(resultado.getDate("fechafirma").toLocalDate()),
                        new FechaInicio(resultado.getDate("fechaIncio").toLocalDate()),
                        new FechaFin(resultado.getDate("fechaFin").toLocalDate()),
                        Estado.valueOf(resultado.getString("estado"))
                );
                listaContratos.add(contratoConsultado);
            }

            // Devuelve el arreglo con los resultados (o vacío si no encontró nada, sin romper el flujo)
            return listaContratos.toArray(new Contrato[0]);

        } catch (Exception error) {
            throw new Exception("Error al consultar contratos: " + error.getMessage());
        } finally {
            if (baseDato != null) {
                baseDato.desconectar();
            }
        }
    }


    public static Contrato[] consultarContratosPorUsuario(IdUsuario idUsuario) throws Exception { /* Tu código original... */ return new Contrato[0]; }
    public static Contrato[] listarTodosLosContratos() throws Exception { /* Tu código original... */ return new Contrato[0]; }
}