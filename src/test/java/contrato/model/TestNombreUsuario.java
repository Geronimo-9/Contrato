package contrato.model;

import org.contrato.model.domain.exception.FormatoInvalidoExcepcion;
import org.contrato.model.domain.exception.ParametrosVaciosExcepcion;
import org.contrato.model.domain.valueobject.usuario.NombreUsuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestNombreUsuario {


    //Nota para pensar: Qué tiene que hacer mi test? debo tener en cuenta estas pruebas "rigurosa":

    //No validar numeros, espacios, caracteres especiales, nombre+cualquier chirrete+ nulos o vacios.


    @Test
    public void invalidaNombreConNumeros() {
        assertThrows(
                FormatoInvalidoExcepcion.class,
                () -> new NombreUsuario("Juan123")
        );
    }

    @Test
    public void invalidaCaracteresEspeciales() {
        assertThrows(
                FormatoInvalidoExcepcion.class,
                () -> new NombreUsuario("Juan@")
        );
    }

    @Test
    public void invalidaNombresVacios() {
        assertThrows(
                ParametrosVaciosExcepcion.class,
                () -> new NombreUsuario("")
        );
    }

    @Test
    public void invalidaEspacios() {
        assertThrows(
                ParametrosVaciosExcepcion.class,
                () -> new NombreUsuario("   ")
        );
    }

    @Test
    public void invalidaValorNulo() {
        assertThrows(
                NullPointerException.class,
                () -> new NombreUsuario(null)
        );
    }


}
