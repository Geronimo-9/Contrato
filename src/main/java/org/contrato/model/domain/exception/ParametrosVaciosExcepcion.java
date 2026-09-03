package org.contrato.model.domain.exception;

public class ParametrosVaciosExcepcion extends DomainException {

    public static final String PARAMETRO_VACIO = "El parametro '%s' No puede quedar vacio";
    public static final String PARAMETRO_NULO = "El parametro '%s' está nulo";

    public  ParametrosVaciosExcepcion(final String message) {
        super(message);
    }


    public static ParametrosVaciosExcepcion parametroVacio(String valorInvalido) {
        return new ParametrosVaciosExcepcion(String.format(PARAMETRO_VACIO, valorInvalido));
    }


    public static ParametrosVaciosExcepcion parametroNulo(String valorInvalido) {
        return new ParametrosVaciosExcepcion(String.format(PARAMETRO_NULO, valorInvalido));
    }



}
