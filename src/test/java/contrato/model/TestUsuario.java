package contrato.model;

import org.contrato.model.domain.entity.Usuario;
import org.contrato.model.domain.exception.CantidadRequeridaExcepcion;
import org.contrato.model.domain.exception.FormatoInvalidoExcepcion;
import org.contrato.model.domain.exception.ParametrosVaciosExcepcion;
import org.contrato.model.domain.valueobject.usuario.ContrasenaUsuario;
import org.contrato.model.domain.valueobject.usuario.IdUsuario;
import org.contrato.model.domain.valueobject.usuario.NombreUsuario;
import org.contrato.model.domain.valueobject.usuario.RolUsuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestUsuario {

    @Test
    public void crearUsuarioIdInvalida() {

        assertThrows(
                CantidadRequeridaExcepcion.class,
                () -> {
                    IdUsuario id = new IdUsuario("1");
                    NombreUsuario nombre = new NombreUsuario("Carlos");
                    ContrasenaUsuario contrasena = new ContrasenaUsuario("12345");

                    new Usuario(
                            id,
                            nombre,
                            contrasena,
                            RolUsuario.PENDIENTE
                    );
                }
        );
    }


    @Test
    public void crearUsuarioNombreInvalido() {

        assertThrows(
                FormatoInvalidoExcepcion.class,
                () -> {
                    IdUsuario idUsuario = new IdUsuario("1234567890");
                    NombreUsuario nombreUsuario = new NombreUsuario("C4rl0590");
                    ContrasenaUsuario contrasenaUsuario =
                            new ContrasenaUsuario("DesarrolloLentejas");

                    new Usuario(
                            idUsuario,
                            nombreUsuario,
                            contrasenaUsuario,
                            RolUsuario.PENDIENTE
                    );
                }
        );
    }


    @Test
    public void crearUsuarioContrasenaVacia() {

        assertThrows(
                ParametrosVaciosExcepcion.class,
                () -> {
                    IdUsuario idUsuario = new IdUsuario("1234567890");
                    NombreUsuario nombreUsuario = new NombreUsuario("Carlos");
                    ContrasenaUsuario contrasenaUsuario =
                            new ContrasenaUsuario("");

                    new Usuario(
                            idUsuario,
                            nombreUsuario,
                            contrasenaUsuario,
                            RolUsuario.PENDIENTE
                    );
                }
        );
    }


    @Test
    public void crearUsuarioCamposNulos() {

        assertThrows(
                NullPointerException.class,
                () -> {
                    IdUsuario idUsuario = new IdUsuario(null);
                    NombreUsuario nombreUsuario = new NombreUsuario(null);
                    ContrasenaUsuario contrasenaUsuario =
                            new ContrasenaUsuario(null);

                    new Usuario(
                            idUsuario,
                            nombreUsuario,
                            contrasenaUsuario,
                            RolUsuario.PENDIENTE
                    );
                }
        );
    }


    @ParameterizedTest
    @ValueSource(strings = {"PENDIENTE", "USUARIO", "EMPRESA"})
    public void crearUsuarioValido(String rol) {

        IdUsuario id = new IdUsuario("1234567890");
        NombreUsuario nombre = new NombreUsuario("Carlos");
        ContrasenaUsuario contrasena = new ContrasenaUsuario("12345");

        Usuario usuario = new Usuario(
                id,
                nombre,
                contrasena,
                RolUsuario.valueOf(rol)
        );

        assertEquals(id, usuario.getId());
        assertEquals(nombre, usuario.getNombre());
        assertEquals(contrasena, usuario.getContrasena());
        assertEquals(RolUsuario.valueOf(rol), usuario.getRol());


    }

}





