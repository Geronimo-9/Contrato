package contrato.model;

import org.contrato.model.domain.exception.ValoresNegativosExcepcion;
import org.contrato.model.domain.valueobject.contrato.Monto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestMontoContrato {


    @Test
    void montoNegativo() {
        assertThrows(
                ValoresNegativosExcepcion.class,
                () -> new Monto(-1)
        );
    }

    @Test
    void montoValido() {
        assertDoesNotThrow(
                () -> new Monto(100)
        );
    }




}
