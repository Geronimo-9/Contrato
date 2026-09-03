package org.contrato.model.domain.valueobject.contrato;

import org.contrato.model.domain.exception.FormatoInvalidoExcepcion;
import org.contrato.model.domain.exception.ParametrosVaciosExcepcion;

public record FechaFin(String fecha) {
    private static final String FORMATO_FECHA = "\\d{2}/\\d{2}/\\d{4}"; //dd/mm/aaaa

    public FechaFin{
        validarFechaNula(fecha);
        validarFechaVacia(fecha);
        validarFormatoFechaInvalida(fecha);
    }



    private static void validarFechaVacia(final String fecha){
        if (fecha.isEmpty()) {
            throw ParametrosVaciosExcepcion.parametroVacio(fecha);
        }
    }


    private static void validarFormatoFechaInvalida(final String fecha){
        if (!fecha.matches(FORMATO_FECHA)) {
            throw FormatoInvalidoExcepcion.formatoIncorrecto(fecha);
        }
    }



    private static void validarFechaNula(final String valores){
        if (valores == null) {
            throw ParametrosVaciosExcepcion.parametroNulo(valores);
        }
    }



}
