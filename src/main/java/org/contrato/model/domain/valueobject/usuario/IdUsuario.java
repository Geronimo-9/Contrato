package org.contrato.model.domain.valueobject.usuario;

import org.contrato.model.domain.exception.CantidadRequeridaExcepcion;
import org.contrato.model.domain.exception.ParametrosVaciosExcepcion;

import java.util.Objects;


public record IdUsuario(String valores) {


    public static final String CANTIDAD_DIGITO_CEDULA = "^\\d{10}$";
    public static final String CANTIDAD_NULA =  null;

    public IdUsuario{

        final String normalizarValor= Objects.requireNonNull(valores, "Debe ingresar tu documento de identidad").trim();
        validarSinEmpezar(normalizarValor);
        validarCantidadRequerida(normalizarValor);
        validarValorNulo(normalizarValor);
        valores = normalizarValor;

    }




    public static void validarSinEmpezar(final String normalizarValor){
        if (normalizarValor.isEmpty()) {
            throw ParametrosVaciosExcepcion.parametroVacio(normalizarValor);
        }
    }


public static void validarCantidadRequerida(final String valores){
        if (!valores.matches(CANTIDAD_DIGITO_CEDULA)){
            throw CantidadRequeridaExcepcion.cantidadRequerida(valores);
        }
}

public static void validarValorNulo(final String valores){
    if (valores.equals(CANTIDAD_NULA)) {
        throw ParametrosVaciosExcepcion.parametroNulo(valores);
    }
}



}
