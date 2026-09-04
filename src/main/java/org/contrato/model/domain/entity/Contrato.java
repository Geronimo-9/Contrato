package org.contrato.model.domain.entity;


import org.contrato.model.domain.exception.FechaInvalidaExcepcion;
import org.contrato.model.domain.valueobject.contrato.*;



public class Contrato{

    Empresa empresa;
    Empleado empleado;
    Funciones funciones;
    Monto monto;
    FrecuenciaPago frecuenciaPago;
    FechaFirma fechaFirma;
    FechaInicio fechaInicio;
    FechaFin fechaFin;
    Estado estado;



    //Creacion de Contrato Sin estados
    public Contrato(Empresa empresa,
                    Empleado empleado,
                    Funciones funciones,
                    Monto monto,
                    FrecuenciaPago frecuenciaPago,
                    FechaFirma fechaFirma,
                    FechaInicio fechaInicio,
                    FechaFin fechaFin,
                    Estado estado) {
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
    public Contrato(Empresa empresa,
                    Empleado empleado,
                    Funciones funciones,
                    Monto monto,
                    FrecuenciaPago frecuenciaPago,
                    FechaFirma fechaFirma,
                    FechaInicio fechaInicio,
                    FechaFin fechaFin) {
        this.empresa = empresa;
        this.empleado = empleado;
        this.funciones = funciones;
        this.monto = monto;
        this.frecuenciaPago = frecuenciaPago;
        this.fechaFirma = fechaFirma;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
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




    public Contrato crearContrato(){
        return new Contrato(empresa,empleado,funciones,monto,frecuenciaPago,fechaFirma,fechaInicio,fechaFin);
    }

    public Contrato contratoVencido(){
        return new Contrato(empresa,empleado,funciones,monto,frecuenciaPago,fechaFirma,fechaInicio,fechaFin,Estado.VENCIDO);
    }


    public Contrato contratoVigente(){
        return new Contrato(empresa,empleado,funciones,monto,frecuenciaPago,fechaFirma,fechaInicio,fechaFin,Estado.VIGENTE);

    }


    // Regla: Fecha fin no puede estar antes de fecha inicio.
    public void validarFechas(FechaInicio fechaInicio, FechaFin fechaFin) {
        if (fechaFin.fecha().isBefore(fechaInicio.fecha())) {
            throw FechaInvalidaExcepcion.fechaInconsistente();
        }
    }



}