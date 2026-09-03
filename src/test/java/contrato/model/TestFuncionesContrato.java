package contrato.model;


import org.contrato.model.domain.exception.ParametrosVaciosExcepcion;
import org.contrato.model.domain.valueobject.contrato.Funciones;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestFuncionesContrato {

    @Test
    void textoNulo() {
        assertThrows(
                ParametrosVaciosExcepcion.class,
                () -> new Funciones(null)
        );
    }

    @Test
    void textoVacio() {
        assertThrows(
                ParametrosVaciosExcepcion.class,
                () -> new Funciones("")
        );
    }

    @Test
    void textoValido() {
        assertDoesNotThrow(
                () -> new Funciones("Realizar mantenimiento preventivo de equipos")
        );
    }
}
