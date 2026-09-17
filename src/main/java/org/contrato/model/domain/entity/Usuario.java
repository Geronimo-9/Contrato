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

    public void setId(IdUsuario id) {
        this.id = id;
    }

    public void setNombre(NombreUsuario nombre) {
        this.nombre = nombre;
    }

    public void setContrasena(ContrasenaUsuario contrasena) {
        this.contrasena = contrasena;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }




}
