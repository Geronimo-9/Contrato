package org.contrato.model.persistence;

import org.contrato.model.domain.entity.Usuario;
import org.contrato.model.domain.valueobject.usuario.ContrasenaUsuario;
import org.contrato.model.domain.valueobject.usuario.IdUsuario;
import org.contrato.model.domain.valueobject.usuario.NombreUsuario;
import org.contrato.model.domain.valueobject.usuario.RolUsuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CRUDUsuario {

    private Usuario usuario;
    private DataBaseConfig baseDeDatos;

    public CRUDUsuario() {
        this.usuario = new Usuario();
        try {
            this.baseDeDatos = new DataBaseConfig();
        } catch (Exception e) {
            System.out.println("Error al instanciar base de datos: " + e.getMessage());
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }



    public void agregarUsuario() throws Exception {
        if (usuario.getId().valores() == null || usuario.getId().valores().isEmpty()) {
            throw new Exception("La ID del usuario es necesaria");
        }

        String sqlInsert = "INSERT INTO Usuario (id, nombre, contrasena, rol) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement sentenciaSQL = baseDeDatos.crearSentencias(sqlInsert);
            sentenciaSQL.setString(1, usuario.getId().valores());
            sentenciaSQL.setString(2, usuario.getNombre().valores());
            sentenciaSQL.setString(3, usuario.getContrasena().valores());
            sentenciaSQL.setString(4, usuario.getRol().toString());

            baseDeDatos.actualizar(sentenciaSQL);
        } catch (Exception error) {
            throw new Exception("Error al agregar el Usuario " + usuario.getId() + " <br/> Explicacion: " + error);
        } finally {
            baseDeDatos.desconectar();
        }
    }



    public void modificarUsuario() throws Exception {
        if (usuario.getId().valores() == null || usuario.getId().valores().isEmpty()) {
            throw new Exception("La ID del usuario es necesaria");
        }

        String sqlUpdate = "UPDATE Usuario SET nombre=?, contrasena=?, rol=? WHERE id=?";

        try {
            PreparedStatement sentenciaSQL = baseDeDatos.crearSentencias(sqlUpdate);
            sentenciaSQL.setString(4, usuario.getId().valores());
            sentenciaSQL.setString(1, usuario.getNombre().valores());
            sentenciaSQL.setString(2, usuario.getContrasena().valores());
            sentenciaSQL.setString(3, usuario.getRol().toString());

            baseDeDatos.actualizar(sentenciaSQL);
        } catch (Exception error) {
            throw new Exception("Error al actualizar el Usuario " + usuario.getId() + " <br/> Explicacion: " + error);
        } finally {
            baseDeDatos.desconectar();
        }
    }



    public void eliminarUsuario() throws Exception {
        if (usuario.getId().valores() == null || usuario.getId().valores().isEmpty()) {
            throw new Exception("La ID del usuario es necesaria");
        }

        String sqlDelete = "DELETE FROM Usuario WHERE id=?";

        try {
            PreparedStatement sentenciaSQL = baseDeDatos.crearSentencias(sqlDelete);
            sentenciaSQL.setString(1, usuario.getId().valores());
            baseDeDatos.actualizar(sentenciaSQL);
        } catch (Exception error) {
            throw new Exception("Error al eliminar el Usuario " + usuario.getId() + " <br/> Explicacion: " + error);
        } finally {
            baseDeDatos.desconectar();
        }
    }



    public static Usuario iniciarSesion(IdUsuario id, ContrasenaUsuario contrasena) throws Exception {
        if (id.valores() == null || id.valores().isEmpty() || contrasena.valores() == null || contrasena.valores().isEmpty()) {
            throw new Exception("El ID y la contraseña son necesarios");
        }

        DataBaseConfig baseDato = null;
        String sqlSelect = "SELECT * FROM Usuario WHERE id=? AND contrasena=?";

        try {
            baseDato = new DataBaseConfig();
            PreparedStatement sentenciaSQL = baseDato.crearSentencias(sqlSelect);
            sentenciaSQL.setString(1, id.valores());
            sentenciaSQL.setString(2, contrasena.valores());

            ResultSet resultado = baseDato.consultar(sentenciaSQL);

            if (resultado.next()) {
                Usuario usuarioLogueado = new Usuario();
                usuarioLogueado.setId(new IdUsuario(resultado.getString("id")));
                usuarioLogueado.setNombre(new NombreUsuario(resultado.getString("nombre")));
                usuarioLogueado.setContrasena(new ContrasenaUsuario(resultado.getString("contrasena")));
                usuarioLogueado.setRol(RolUsuario.valueOf(resultado.getString("rol")));
                return usuarioLogueado;
            } else {
                throw new Exception("Credenciales incorrectas.");
            }
        } catch (Exception error) {
            throw new Exception("Error en el ID o password: " + error.getMessage());
        } finally {
            if (baseDato != null) {
                baseDato.desconectar();
            }
        }
    }



    public static Usuario consultarUsuario(IdUsuario id) throws Exception {
        if (id.valores() == null || id.valores().isEmpty()) {
            throw new Exception("La ID del usuario es necesaria");
        }

        DataBaseConfig baseDato = null;
        String sqlSelect = "SELECT * FROM Usuario WHERE id=?";

        try {
            baseDato = new DataBaseConfig();
            PreparedStatement sentenciaSQL = baseDato.crearSentencias(sqlSelect);
            sentenciaSQL.setString(1, id.valores());

            ResultSet resultado = baseDato.consultar(sentenciaSQL);
            if (resultado.next()) {
                Usuario usuarioConsultado = new Usuario();
                usuarioConsultado.setId(new IdUsuario(resultado.getString("id")));
                usuarioConsultado.setNombre(new NombreUsuario(resultado.getString("nombre")));
                usuarioConsultado.setContrasena(new ContrasenaUsuario(resultado.getString("contrasena")));
                usuarioConsultado.setRol(RolUsuario.valueOf(resultado.getString("rol")));
                return usuarioConsultado;
            } else {
                throw new Exception("Usuario no encontrado.");
            }
        } catch (Exception error) {
            throw new Exception("Error al consultar: " + error.getMessage());
        } finally {
            if (baseDato != null) {
                baseDato.desconectar();
            }
        }
    }



    public static Usuario[] listarUsuarios() throws Exception {
        DataBaseConfig baseDato = null;
        String sqlSelect = "SELECT * FROM Usuario";

        try {
            baseDato = new DataBaseConfig();
            PreparedStatement sentenciaSQL = baseDato.crearSentencias(sqlSelect);
            ResultSet resultado = baseDato.consultar(sentenciaSQL);

            ArrayList<Usuario> lista = new ArrayList<>();

            while (resultado.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(new IdUsuario(resultado.getString("id")));
                usuario.setNombre(new NombreUsuario(resultado.getString("nombre")));
                usuario.setContrasena(new ContrasenaUsuario(resultado.getString("contrasena")));
                usuario.setRol(RolUsuario.valueOf(resultado.getString("rol")));
                lista.add(usuario);
            }

            if (lista.isEmpty()) {
                throw new Exception("No hay usuarios registrados.");
            }

            return lista.toArray(new Usuario[0]);

        } catch (Exception error) {
            throw new Exception("Error al listar los usuarios: " + error.getMessage());
        } finally {
            if (baseDato != null) {
                baseDato.desconectar();
            }
        }
    }
}