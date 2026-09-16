package org.contrato.model.domain.entity;


import org.contrato.model.domain.exception.FechaInvalidaExcepcion;
import org.contrato.model.domain.valueobject.contrato.*;
import org.contrato.model.domain.valueobject.usuario.IdUsuario;


public class Contrato{

    IdUsuario idUsuario; //---Foreing Key
    Empresa empresa;
    Empleado empleado;
    Funciones funciones;
    Monto monto;
    FrecuenciaPago frecuenciaPago;
    FechaFirma fechaFirma;
    FechaInicio fechaInicio;
    FechaFin fechaFin;
    Estado estado;

    public Contrato() {
    }

    //Creacion de Contrato Sin estados
    public Contrato(IdUsuario idUsuario,
                    Empresa empresa,
                    Empleado empleado,
                    Funciones funciones,
                    Monto monto,
                    FrecuenciaPago frecuenciaPago,
                    FechaFirma fechaFirma,
                    FechaInicio fechaInicio,
                    FechaFin fechaFin,
                    Estado estado) {
        this.idUsuario = idUsuario;
        this.empresa = empresa;
        this.empleado = empleado;
        this.funciones = funciones;
        this.monto = monto;
        this.frecuenciaPago = frecuenciaPago;
        this.fechaFirma = fechaFirma;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    // Contrato cuando está diligenciado
    public Contrato(IdUsuario idUsuario,
                    Empresa empresa,
                    Empleado empleado,
                    Funciones funciones,
                    Monto monto,
                    FrecuenciaPago frecuenciaPago,
                    FechaFirma fechaFirma,
                    FechaInicio fechaInicio,
                    FechaFin fechaFin) {
        this.idUsuario = idUsuario;
        this.empresa = empresa;
        this.empleado = empleado;
        this.funciones = funciones;
        this.monto = monto;
        this.frecuenciaPago = frecuenciaPago;
        this.fechaFirma = fechaFirma;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public IdUsuario getIdUsuario() {
        return idUsuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public Funciones getFunciones() {
        return funciones;
    }

    public Monto getMonto() {
        return monto;
    }

    public FrecuenciaPago getFrecuenciaPago() {
        return frecuenciaPago;
    }

    public FechaFirma getFechaFirma() {
        return fechaFirma;
    }

    public FechaInicio getFechaInicio() {
        return fechaInicio;
    }

    public FechaFin getFechaFin() {
        return fechaFin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setIdUsuario(IdUsuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public void setFunciones(Funciones funciones) {
        this.funciones = funciones;
    }

    public void setMonto(Monto monto) {
        this.monto = monto;
    }

    public void setFrecuenciaPago(FrecuenciaPago frecuenciaPago) {
        this.frecuenciaPago = frecuenciaPago;
    }

    public void setFechaFirma(FechaFirma fechaFirma) {
        this.fechaFirma = fechaFirma;
    }

    public void setFechaInicio(FechaInicio fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(FechaFin fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Contrato crearContrato(){
        return new Contrato(idUsuario,empresa,empleado,funciones,monto,frecuenciaPago,fechaFirma,fechaInicio,fechaFin);
    }

    public Contrato contratoVencido(){
        return new Contrato(idUsuario,empresa,empleado,funciones,monto,frecuenciaPago,fechaFirma,fechaInicio,fechaFin,Estado.VENCIDO);
    }


    public Contrato contratoVigente(){
        return new Contrato(idUsuario,empresa,empleado,funciones,monto,frecuenciaPago,fechaFirma,fechaInicio,fechaFin,Estado.VIGENTE);

    }


    // Regla: Fecha fin no puede estar antes de fecha inicio.
    public void validarFechas(FechaInicio fechaInicio, FechaFin fechaFin) {
        if (fechaFin.fecha().isBefore(fechaInicio.fecha())) {
            throw FechaInvalidaExcepcion.fechaInconsistente();
        }
    }



}