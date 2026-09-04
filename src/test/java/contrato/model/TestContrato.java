package contrato.model;

import org.contrato.model.domain.entity.Contrato;
import org.contrato.model.domain.exception.*;
import org.contrato.model.domain.valueobject.contrato.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestContrato {


    @Test
    void nombreEmpleadoSinSeguirReglas() {

        assertThrows(
                FormatoInvalidoExcepcion.class,
                () -> new Contrato(
                        new Empresa("Google"),
                        new Empleado("Empleado123"),
                        new Funciones("Desarrollar software"),
                        new Monto(2000000),
                        FrecuenciaPago.MENSUAL,
                        new FechaFirma(LocalDate.of(2026, 9, 4)),
                        new FechaInicio(LocalDate.of(2026, 9, 4)),
                        new FechaFin(LocalDate.of(2026, 10, 4))
                )
        );
    }



    @Test
    void nombreEmpleadoNulo() {

        assertThrows(
                ValoresNulosExcepcion.class,
                () -> new Contrato(
                        new Empresa("Google"),
                        new Empleado(null),
                        new Funciones("Desarrollar software"),
                        new Monto(2000000),
                        FrecuenciaPago.MENSUAL,
                        new FechaFirma(LocalDate.of(2026, 9, 4)),
                        new FechaInicio(LocalDate.of(2026, 9, 4)),
                        new FechaFin(LocalDate.of(2026, 10, 4))
                )
        );
    }


    @Test
    void nombreEmpresaNula() {

        assertThrows(
                ValoresNulosExcepcion.class,
                () -> new Contrato(
                        new Empresa(null),
                        new Empleado("Empleado"),
                        new Funciones("Desarrollar software"),
                        new Monto(2000000),
                        FrecuenciaPago.MENSUAL,
                        new FechaFirma(LocalDate.of(2026, 9, 4)),
                        new FechaInicio(LocalDate.of(2026, 9, 4)),
                        new FechaFin(LocalDate.of(2026, 10, 4))
                )
        );
    }

    @Test
    void nombreEmpresaSinSeguirReglas() {

        assertThrows(
                FormatoInvalidoExcepcion.class,
                () -> new Contrato(
                        new Empresa("Google123"),
                        new Empleado("Carlos"),
                        new Funciones("Desarrollar software"),
                        new Monto(2000000),
                        FrecuenciaPago.MENSUAL,
                        new FechaFirma(LocalDate.of(2026, 9, 4)),
                        new FechaInicio(LocalDate.of(2026, 9, 4)),
                        new FechaFin(LocalDate.of(2026, 10, 4))
                )
        );
    }

    @Test
    void crearFechasNulas() {

        assertThrows(
                ValoresNulosExcepcion.class,
                () -> new Contrato(
                        new Empresa("Google"),
                        new Empleado("Carlos"),
                        new Funciones("Desarrollar software"),
                        new Monto(2000000),
                        FrecuenciaPago.MENSUAL,
                        new FechaFirma(null),
                        new FechaInicio(LocalDate.of(2026, 9, 4)),
                        new FechaFin(LocalDate.of(2026, 10, 4))
                )
        );

        assertThrows(
                ValoresNulosExcepcion.class,
                () -> new Contrato(
                        new Empresa("Google"),
                        new Empleado("Carlos"),
                        new Funciones("Desarrollar software"),
                        new Monto(2000000),
                        FrecuenciaPago.MENSUAL,
                        new FechaFirma(LocalDate.of(2026, 9, 4)),
                        new FechaInicio(null),
                        new FechaFin(LocalDate.of(2026, 10, 4))
                )
        );

        assertThrows(
                ValoresNulosExcepcion.class,
                () -> new Contrato(
                        new Empresa("Google"),
                        new Empleado("Carlos"),
                        new Funciones("Desarrollar software"),
                        new Monto(2000000),
                        FrecuenciaPago.MENSUAL,
                        new FechaFirma(LocalDate.of(2026, 9, 4)),
                        new FechaInicio(LocalDate.of(2026, 9, 4)),
                        new FechaFin(null)
                )
        );
    }

    @Test
    void funcionesCamposVacios() {

        assertThrows(
                ParametrosVaciosExcepcion.class,
                () -> new Contrato(
                        new Empresa("Google"),
                        new Empleado("Carlos"),
                        new Funciones(""),
                        new Monto(2000000),
                        FrecuenciaPago.MENSUAL,
                        new FechaFirma(LocalDate.of(2026, 9, 4)),
                        new FechaInicio(LocalDate.of(2026, 9, 4)),
                        new FechaFin(LocalDate.of(2026, 10, 4))
                )
        );
    }


    @Test
    void funcionesCamposNulo() {

        assertThrows(
                ValoresNulosExcepcion.class,
                () -> new Contrato(
                        new Empresa("Google"),
                        new Empleado("Carlos"),
                        new Funciones(null),
                        new Monto(2000000),
                        FrecuenciaPago.MENSUAL,
                        new FechaFirma(LocalDate.of(2026, 9, 4)),
                        new FechaInicio(LocalDate.of(2026, 9, 4)),
                        new FechaFin(LocalDate.of(2026, 10, 4))
                )
        );
    }

    @Test
    void funcionesComoNulo() {

        assertThrows(
                ValoresNulosExcepcion.class,
                () -> new Contrato(
                        new Empresa("Google"),
                        new Empleado("Carlos"),
                        new Funciones(null),
                        new Monto(2000000),
                        FrecuenciaPago.MENSUAL,
                        new FechaFirma(LocalDate.of(2026, 9, 4)),
                        new FechaInicio(LocalDate.of(2026, 9, 4)),
                        new FechaFin(LocalDate.of(2026, 10, 4))
                )
        );
    }

    @Test
    void montoNegativo() {

        assertThrows(
                ValoresNegativosExcepcion.class,
                () -> new Contrato(
                        new Empresa("Google"),
                        new Empleado("Carlos"),
                        new Funciones("Desarrollar software"),
                        new Monto(-2000000),
                        FrecuenciaPago.MENSUAL,
                        new FechaFirma(LocalDate.of(2026, 9, 4)),
                        new FechaInicio(LocalDate.of(2026, 9, 4)),
                        new FechaFin(LocalDate.of(2026, 10, 4))
                )
        );
    }




    @Test
    void crearContrato() {

        Contrato contrato = new Contrato(
                new Empresa("Google"),
                new Empleado("Carlos"),
                new Funciones("Desarrollar software"),
                new Monto(2000000),
                FrecuenciaPago.MENSUAL,
                new FechaFirma(LocalDate.of(2026, 9, 4)),
                new FechaInicio(LocalDate.of(2026, 9, 4)),
                new FechaFin(LocalDate.of(2026, 10, 4))
        );

        assertNotNull(contrato);
        assertNull(contrato.getEstado());
    }



    @ParameterizedTest
    @MethodSource("estadosContrato")
    void crearContrato(Estado estado) {

        Contrato contrato = new Contrato(
                new Empresa("Google"),
                new Empleado("Carlos"),
                new Funciones("Desarrollar software"),
                new Monto(2000000),
                FrecuenciaPago.MENSUAL,
                new FechaFirma(LocalDate.of(2026, 9, 4)),
                new FechaInicio(LocalDate.of(2026, 9, 4)),
                new FechaFin(LocalDate.of(2026, 10, 4))
        );

        Contrato resultado = estado == Estado.VIGENTE
                ? contrato.contratoVigente()
                : contrato.contratoVencido();

        assertNotNull(resultado);
        assertEquals(estado, resultado.getEstado());
    }

    static Stream<Estado> estadosContrato() {
        return Stream.of(
                Estado.VIGENTE,
                Estado.VENCIDO
        );
    }



}











