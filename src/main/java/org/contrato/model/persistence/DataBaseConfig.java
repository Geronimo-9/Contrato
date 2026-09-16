package org.contrato.model.persistence;

import java.sql.*;

public class DataBaseConfig {

    private String driver = "org.postgresql.Driver";
    private static String url = "jdbc:postgresql://";
    protected String nombreIpServidor = "ep-bold-water-b59q8qvd-pooler.c-7.us-east-2.aws.neon.tech";
    protected int puerto = 5432;
    protected String nombreUsuario = "neondb_owner";
    protected String contrasena = "npg_gqYsDAW1T7vh";
    protected String nombreBD = "neondb";
    private ResultSet filasConsulta;
    private Connection conexion;
    private PreparedStatement sentencias;



    public DataBaseConfig() throws Exception {
        url = "jdbc:postgresql://" + nombreIpServidor + ":" + puerto + "/" + nombreBD;
        this.conectar();
    }



    public void conectar() throws Exception {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new Exception("Error al conectar la base de datos: " + e.getMessage());
        }

        try {
            conexion = DriverManager.getConnection(url, nombreUsuario, contrasena);
        } catch (SQLException e) {
            throw new Exception("Error de conexion \n Codigo:" + e.getErrorCode() + " Explicacion:" + e.getMessage());
        }
    }

    public void desconectar() {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            conexion = null;
        }
    }




    public int actualizar(PreparedStatement sentencias) throws Exception {
        try {
            return sentencias.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al ejectuar sentencia BD conexion \n Codigo:" + e.getErrorCode() + " Explicacion:" + e.getMessage());
        }
    }

    public PreparedStatement crearSentencias(String sql) throws Exception {
        try {
            return conexion.prepareStatement(sql);
        } catch (SQLException ex) {
            throw new SQLException("Error de sentencia BD \n codigo:" + ex.getErrorCode() + " Explicacion:" + ex.getMessage());
        }
    }

    public ResultSet consultar(PreparedStatement sentencia) throws Exception {
        try {
            return sentencia.executeQuery();
        } catch (SQLException ex) {
            throw new SQLException("Error al ejecutar sentencia BD conexion: " + ex.getMessage());
        }
    }



    public String getDriver() { return driver; }
    public String getNombreIpServidor() { return nombreIpServidor; }
    public int getPuerto() { return puerto; }
    public String getNombreUsuario() { return nombreUsuario; }
    public String getContrasena() { return contrasena; }
    public String getNombreBD() { return nombreBD; }
    public ResultSet getFilasConsulta() { return filasConsulta; }

    public void setDriver(String driver) { this.driver = driver; }
    public void setNombreIpServidor(String nombreIpServidor) { this.nombreIpServidor = nombreIpServidor; }
    public void setPuerto(int puerto) { this.puerto = puerto; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    public void setContraseña(String contrasena) { this.contrasena = contrasena; }
    public void setNombreBD(String nombreBD) { this.nombreBD = nombreBD; }
    public void setFilasConsulta(ResultSet filasConsulta) { this.filasConsulta = filasConsulta; }
}