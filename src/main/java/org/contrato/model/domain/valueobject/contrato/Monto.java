package org.contrato.model.domain.valueobject.contrato;

import org.contrato.model.domain.exception.ValoresNegativosExcepcion;

public class Monto {

    public double monto;



    public Monto(double monto){
        setMonto(monto);
    }




    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        validarNegativos(monto);
        this.monto = monto;
    }


    private static void validarNegativos(double monto){
        if(monto<0){
            throw ValoresNegativosExcepcion.valorNegativo(monto);
        }

    }


}
