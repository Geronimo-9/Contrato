package org.contrato.model.domain.valueobject.usuario;

import org.contrato.model.domain.exception.CantidadRequeridaExcepcion;
import org.contrato.model.domain.exception.ParametrosVaciosExcepcion;

public record ContrasenaUsuario(String valores) {

    public static final int MAXIMA_LONGITUD = 10;
    public static final int MINIMA_LONGITUD = 5;
    public static final String CONTRASEÑA_NULA =  null;
    public static final String CONTRASEÑA_VACIA =  "";

    public ContrasenaUsuario{

    validarCampoNulo(valores);
    validarCampoVacio(valores);
    validarTamañoParametro(valores);


    }


    public static void validarTamañoParametro(String valores){
        if (valores.length() <= MINIMA_LONGITUD || MAXIMA_LONGITUD <= valores.length()) {
            throw CantidadRequeridaExcepcion.fueraDeRango(valores);
        }
    }

    public static void validarCampoNulo(String valores){
        if (valores == CONTRASEÑA_NULA) {
            throw ParametrosVaciosExcepcion.parametroNulo(valores);
        }
    }

    public static void validarCampoVacio(String valores){
        if (valores.equals(CONTRASEÑA_VACIA)) {
            throw ParametrosVaciosExcepcion.parametroVacio(valores);
        }
    }


}
