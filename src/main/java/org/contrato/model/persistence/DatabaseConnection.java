package org.contrato.model.persistence;

import java.sql.Connection;


public interface DatabaseConnection {
    Connection getConnection();
    void closeConnection();
}
