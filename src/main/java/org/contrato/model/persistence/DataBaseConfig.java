package org.contrato.model.persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBaseConfig implements DatabaseConnection {


   private static DataBaseConfig database;
   private Connection connection;


   private static final String url = "jdbc:postgresql://localhost:5432/contrato";

   private DataBaseConfig(String user, String password){

       try{
           this.connection = DriverManager.getConnection(url, user, password);
           System.out.println("Se ha conectado al servidor.");

       } catch (SQLException e) {
           System.out.println("Error al conectar con la base de datos" + e.getMessage());
       }

   }

    public static DataBaseConfig getInstance(String user, String password) {
        try {
            if (database == null || database.connection == null || database.connection.isClosed()) {
                database = new DataBaseConfig(user, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return database;
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public void closeConnection() {
       try{

           if (connection != null && !connection.isClosed()) {
               connection.close();
               System.out.println("Conexión cerrada correctamente.");
           }

       } catch (SQLException e) {
           System.out.println("Error al cerrar la conexion" + e.getMessage());
       }


    }
}
