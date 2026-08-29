package org.contrato.model.domain.exception;

public class CantidadRequeridaExcepcion extends DomainException{

    protected static final String CANTIDAD_REQUERIDA = "La cantidad '$s' de parametros deben ser 10";

    public CantidadRequeridaExcepcion(final String mensaje){
        super(mensaje);
    }


    public static CantidadRequeridaExcepcion cantidadRequerida(final String valorInvalido) {
        return new CantidadRequeridaExcepcion(String.format(CANTIDAD_REQUERIDA, valorInvalido));
    }


}
