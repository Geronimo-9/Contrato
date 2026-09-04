package org.contrato.model.domain.valueobject.contrato;

import org.contrato.model.domain.exception.ParametrosVaciosExcepcion;
import org.contrato.model.domain.exception.ValoresNulosExcepcion;

public record Funciones(String texto) {



    public Funciones{
    validarCampoNulo(texto);
    validarSinEmpezar(texto);



    }

    private static void validarCampoNulo(String valores){
        if (valores == null) {
            throw ValoresNulosExcepcion.parametroNulo(valores);
        }
    }

    private static void validarSinEmpezar(final String normalizarValor){
        if (normalizarValor.isEmpty()) {
            throw ParametrosVaciosExcepcion.parametroVacio(normalizarValor);
        }
    }











}
