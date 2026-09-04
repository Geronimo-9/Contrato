package contrato.model;

import org.contrato.model.domain.exception.FormatoInvalidoExcepcion;
import org.contrato.model.domain.exception.ParametrosVaciosExcepcion;
import org.contrato.model.domain.exception.ValoresNulosExcepcion;
import org.contrato.model.domain.valueobject.contrato.FechaFin;
import org.contrato.model.domain.valueobject.contrato.FechaFirma;
import org.contrato.model.domain.valueobject.contrato.FechaInicio;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestFechasContrato {







        @Test
        void fechaValida() {
            assertDoesNotThrow(() -> {
                new FechaFirma(LocalDate.of(2026, 9, 2));
                new FechaInicio(LocalDate.of(2026, 9, 2));
                new FechaFin(LocalDate.of(2026, 10, 2));
            });
        }


        @Test
        void fechaNula() {
            assertThrows(
                    ValoresNulosExcepcion.class,
                    () -> new FechaFirma(null)
            );

            assertThrows(
                    ValoresNulosExcepcion.class,
                    () -> new FechaInicio(null)
            );

            assertThrows(
                    ValoresNulosExcepcion.class,
                    () -> new FechaFin(null)
            );
        }
    }


