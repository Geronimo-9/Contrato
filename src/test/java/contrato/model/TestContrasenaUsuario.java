package contrato.model;

import org.contrato.model.domain.exception.CantidadRequeridaExcepcion;
import org.contrato.model.domain.exception.ParametrosVaciosExcepcion;
import org.contrato.model.domain.valueobject.usuario.ContrasenaUsuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestContrasenaUsuario {


    @Test
    public void claveFueraRango(){
        assertThrows(
                CantidadRequeridaExcepcion.class,
        () -> new ContrasenaUsuario("1@2"));
    }


    @Test
    public void claveNula(){

        assertThrows(ParametrosVaciosExcepcion.class, ()-> new ContrasenaUsuario(null));


    }

    @Test
    public void claveVacia(){

        assertThrows(ParametrosVaciosExcepcion.class, ()-> new ContrasenaUsuario(""));


    }

    @Test
    public void claveDentroRango(){
        new ContrasenaUsuario("@Kenji");
    }









}
