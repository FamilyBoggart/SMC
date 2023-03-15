package com.example.testeo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import android.os.StrictMode;
import android.util.Log;

public class Database {

        private static final String URL = "jdbc:mysql://192.168.43.206:3306";
        private static final String USER = "root";
        private static final String PASSWORD = null;

    public static Connection conectarBD() throws SQLException {
        /*
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        */
        Connection connection = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa a la base de datos");
            } catch (ClassNotFoundException ex) {
                System.out.println("No se pudo encontrar el driver de MySQL");
                ex.printStackTrace();
            } catch (SQLException ex) {
                System.out.println("Error al conectar a la base de datos");

                Log.e("", String.valueOf(ex.getSQLState()));
                Log.e("",ex.getMessage());
            }
            return connection;
        }





}
