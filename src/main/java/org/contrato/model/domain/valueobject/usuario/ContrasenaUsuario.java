package org.contrato.model.domain.valueobject.usuario;

import org.contrato.model.domain.exception.CantidadRequeridaExcepcion;

public record ContrasenaUsuario(String valores) {

    public static final int MAXIMA_LONGITUD = 10;
    public static final int MINIMA_LONGITUD = 5;

    public ContrasenaUsuario{

    validarTamañoParametro(valores);

    }


    public static void validarTamañoParametro(String valores){
        if (valores.length() <= MINIMA_LONGITUD && MAXIMA_LONGITUD <= valores.length()) {
            throw CantidadRequeridaExcepcion.fueraDeRango(valores);
        }
    }



}
