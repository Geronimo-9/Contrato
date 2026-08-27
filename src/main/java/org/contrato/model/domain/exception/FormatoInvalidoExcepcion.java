package org.contrato.model.domain.exception;

public class FormatoInvalidoExcepcion extends DomainException{

    private static final String FORMATO_INCORRECTO= "El identificador '%s' contiene caracteres inválidos. Solo se permiten números.";

    protected FormatoInvalidoExcepcion(final String message) {
        super(message);
    }


    protected static FormatoInvalidoExcepcion formatoIncorrecto(final String invalidValue) {
        return new FormatoInvalidoExcepcion(String.format(FORMATO_INCORRECTO, invalidValue));
    }
}
