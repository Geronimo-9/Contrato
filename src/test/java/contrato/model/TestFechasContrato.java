package contrato.model;

import org.contrato.model.domain.exception.FormatoInvalidoExcepcion;
import org.contrato.model.domain.exception.ParametrosVaciosExcepcion;
import org.contrato.model.domain.valueobject.contrato.FechaFin;
import org.contrato.model.domain.valueobject.contrato.FechaFirma;
import org.contrato.model.domain.valueobject.contrato.FechaInicio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestFechasContrato {

    //ok
    @Test
    void fechaValida() {
        assertDoesNotThrow(() -> {
            new FechaFirma("02/09/2026");
            new FechaInicio("02/09/2026");
            new FechaFin("02/10/2026");
        });
    }

    //ok
    @Test
    void fechaNula() {
        assertThrows(
                ParametrosVaciosExcepcion.class,
                () -> new FechaFirma(null)
        );

        assertThrows(
                ParametrosVaciosExcepcion.class,
                () -> new FechaInicio(null)
        );

        assertThrows(
                ParametrosVaciosExcepcion.class,
                () -> new FechaFin(null)
        );
    }

    //---------
    @Test
    void fechaVacia() {
        assertThrows(
                ParametrosVaciosExcepcion.class,
                () -> new FechaFirma("")
        );

        assertThrows(
                ParametrosVaciosExcepcion.class,
                () -> new FechaInicio("")
        );

        assertThrows(
                ParametrosVaciosExcepcion.class,
                () -> new FechaFin("")
        );
    }

    //ok
    @Test
    void formatoFechaInvalido() {
        assertThrows(
                FormatoInvalidoExcepcion.class,
                () -> new FechaFirma("2026/09/02")
        );

        assertThrows(
                FormatoInvalidoExcepcion.class,
                () -> new FechaInicio("2026/09/02")
        );

        assertThrows(
                FormatoInvalidoExcepcion.class,
                () -> new FechaFin("2026/10/02")
        );
    }

    //ok
    @Test
    void formatoConGuiones() {
        assertThrows(
                FormatoInvalidoExcepcion.class,
                () -> new FechaFirma("02-09-2026")
        );

        assertThrows(
                FormatoInvalidoExcepcion.class,
                () -> new FechaInicio("02-09-2026")
        );

        assertThrows(
                FormatoInvalidoExcepcion.class,
                () -> new FechaFin("02-10-2026")
        );
    }

    //ok
    @Test
    void fechaConLetras() {
        assertThrows(
                FormatoInvalidoExcepcion.class,
                () -> new FechaFirma("02/SEP/2026")
        );

        assertThrows(
                FormatoInvalidoExcepcion.class,
                () -> new FechaInicio("02/SEP/2026")
        );

        assertThrows(
                FormatoInvalidoExcepcion.class,
                () -> new FechaFin("02/OCT/2026")
        );
    }

}
