package contrato.model;

import static org.junit.jupiter.api.Assertions.*;

import org.contrato.model.persistence.DataBaseConfig;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;


public class TestDataBaseConfig {

    @Test
    public void DuplicationConnectionError(){

        DataBaseConfig db1 = DataBaseConfig.getInstance("postgres","1234");
        DataBaseConfig db2 = DataBaseConfig.getInstance("postgres","1234");

        assertSame(db1, db2, "Error: Estas duplicando la base de datos");
    }

    @Test
    public void OpenCloseConnection() throws SQLException {

        DataBaseConfig db1 = DataBaseConfig.getInstance("postgres","1234");

        assertNotNull(db1.getConnection(), "Error: La conexión no debería ser nula");
        assertFalse(db1.getConnection().isClosed(), "Error: La conexión debería estar abierta");

        db1.closeConnection();
        assertTrue(db1.getConnection() == null || db1.getConnection().isClosed(),
                "Error: La conexión sigue abierta después de cerrarla");

    }


}
