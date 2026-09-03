package contrato.model;

import org.contrato.model.domain.exception.FormatoInvalidoExcepcion;
import org.contrato.model.domain.exception.ParametrosVaciosExcepcion;
import org.contrato.model.domain.valueobject.contrato.Empleado;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEmpleadoContrato {

    @Test
    void nombreVacio() {
        assertThrows(
                ParametrosVaciosExcepcion.class,
                () -> new Empleado("")
        );
    }

    @Test
    void nombreSoloEspacios() {
        assertThrows(
                ParametrosVaciosExcepcion.class,
                () -> new Empleado("   ")
        );
    }

    @Test
    void nombreConNumeros() {
        assertThrows(
                FormatoInvalidoExcepcion.class,
                () -> new Empleado("Empleado123")
        );
    }

    @Test
    void nombreConCaracteresEspeciales() {
        assertThrows(
                FormatoInvalidoExcepcion.class,
                () -> new Empleado("Empleado@")
        );
    }

    @Test
    void nombreConEspaciosInternos() {
        assertThrows(
                FormatoInvalidoExcepcion.class,
                () -> new Empleado("Juan Perez")
        );
    }

    @Test
    void nombreValido() {
        assertDoesNotThrow(
                () -> new Empleado("Juan")
        );
    }

    @Test
    void nombreConEspaciosExternos() {
        assertDoesNotThrow(
                () -> new Empleado("   Juan   ")
        );
    }

    @Test
    void nombreNulo() {
        assertThrows(
                NullPointerException.class,
                () -> new Empleado(null)
        );
    }

}