package org.contrato.model.domain.valueobject.contrato;

import org.contrato.model.domain.exception.FormatoInvalidoExcepcion;
import org.contrato.model.domain.exception.ParametrosVaciosExcepcion;

public record FechaFirma(String fecha) {

    public static final String FORMATO_FECHA = "\\d{2}/\\d{2}/\\d{4}"; //dd/mm/aaaa

    public FechaFirma{
        validarFechaNula(fecha);
        validarFormatoFechaInvalida(fecha);

    }



    public static void validarFormatoFechaInvalida(final String fecha){
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
