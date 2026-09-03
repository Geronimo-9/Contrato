package org.contrato.model.domain.entity;

import org.contrato.model.domain.valueobject.usuario.ContrasenaUsuario;
import org.contrato.model.domain.valueobject.usuario.IdUsuario;
import org.contrato.model.domain.valueobject.usuario.NombreUsuario;
import org.contrato.model.domain.valueobject.usuario.RolUsuario;

public class Usuario {

IdUsuario id;
NombreUsuario nombre;
ContrasenaUsuario contrasena;
RolUsuario rol;


    public Usuario(IdUsuario id, NombreUsuario nombre, ContrasenaUsuario contrasena, RolUsuario rol){
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.rol = rol;
    }


    public IdUsuario getId() {
        return id;
    }

    public NombreUsuario getNombre() {
        return nombre;
    }

    public ContrasenaUsuario getContrasena() {
        return contrasena;
    }

    public RolUsuario getRol() {
        return rol;
    }


    public Usuario crearUsuario(IdUsuario id, NombreUsuario nombre, ContrasenaUsuario contrasena, RolUsuario rol){
        return new Usuario(id,nombre,contrasena, RolUsuario.PENDIENTE);
    };


    public Usuario crearUsuarioNormal(IdUsuario id, NombreUsuario nombre, ContrasenaUsuario contrasena, RolUsuario rol){
        return new Usuario(id,nombre,contrasena, RolUsuario.USUARIO);
    };


    public Usuario crearUsuarioEmpresa(IdUsuario id, NombreUsuario nombre, ContrasenaUsuario contrasena, RolUsuario rol){
        return new Usuario(id,nombre,contrasena, RolUsuario.EMPRESA);
    };



}
