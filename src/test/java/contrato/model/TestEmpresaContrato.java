package contrato.model;

import org.contrato.model.domain.exception.FormatoInvalidoExcepcion;
import org.contrato.model.domain.exception.ParametrosVaciosExcepcion;
import org.contrato.model.domain.valueobject.contrato.Empresa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestEmpresaContrato {

    @Test
    void nombreValido() {
        assertDoesNotThrow(
                () -> new Empresa("Microsoft")
        );
    }

    @Test
    void nombreNulo() {
        assertThrows(
                NullPointerException.class,
                () -> new Empresa(null)
        );
    }

    @Test
    void nombreVacio() {
        assertThrows(
                ParametrosVaciosExcepcion.class,
                () -> new Empresa("")
        );
    }

    @Test
    void nombreSoloEspacios() {
        assertThrows(
                ParametrosVaciosExcepcion.class,
                () -> new Empresa("   ")
        );
    }

    @Test
    void nombreConNumeros() {
        assertThrows(
                FormatoInvalidoExcepcion.class,
                () -> new Empresa("Empresa123")
        );
    }

    @Test
    void nombreConCaracteresEspeciales() {
        assertThrows(
                FormatoInvalidoExcepcion.class,
                () -> new Empresa("Empresa@")
        );
    }

    @Test
    void nombreConEspaciosInternos() {
        assertThrows(
                FormatoInvalidoExcepcion.class,
                () -> new Empresa("Empresa Colombia")
        );
    }

    @Test
    void nombreConEspaciosExternos() {
        assertDoesNotThrow(
                () -> new Empresa("   Microsoft   ")
        );
    }

}
