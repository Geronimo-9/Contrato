package org.contrato.model.domain.exception;



public class FechaInvalidaExcepcion extends DomainException{

    protected static final String PLAZOS_INCONSISTENTE = "Los plazos presentan inconsistencias";



    public FechaInvalidaExcepcion(String message) {
        super(message);
    }


    public static FechaInvalidaExcepcion fechaInconsistente(String fecha){
        return new FechaInvalidaExcepcion(String.format(PLAZOS_INCONSISTENTE, fecha));
    }


}
