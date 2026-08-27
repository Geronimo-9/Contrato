package contrato.model;

import org.contrato.model.domain.exception.CantidadRequeridaExcepcion;
import org.contrato.model.domain.valueobject.usuario.IdUsuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestUsuarioId {


        @Test
        public void superarTamañoDigitos() {
            String valorConMasDeDiez = "1245678901234";

            assertThrowsExactly(CantidadRequeridaExcepcion.class, () -> {
                new IdUsuario(valorConMasDeDiez);
            });
        }


        @Test
        public void minimoTamañoDigitos() {
            String valorConMenosDeDiez = "123456789";

            assertThrowsExactly(CantidadRequeridaExcepcion.class, () -> {
                new IdUsuario(valorConMenosDeDiez);
            });
        }


        @Test
        public void valoresNoNumericos() {
            String valorConLetras = "profe, me falta mucha cancha";

            assertThrowsExactly(CantidadRequeridaExcepcion.class, () -> {
                new IdUsuario(valorConLetras);
            });
        }


        @Test
        public void valoresConEspacios() {
            String valorConEspacios = "w   ";

            assertThrowsExactly(CantidadRequeridaExcepcion.class, () -> {
                new IdUsuario(valorConEspacios);
            });
        }


        @Test
        public void idUsuarioValido() {
            String idValido = "1234567890";
            IdUsuario usuario = new IdUsuario(idValido);

            assertEquals(idValido, usuario.valores());
        }


}
