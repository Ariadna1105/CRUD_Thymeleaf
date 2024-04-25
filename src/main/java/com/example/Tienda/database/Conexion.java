package com.example.Tienda.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    String url = "jdbc:mysql://localhost:3306/tienda2";
    String user = "root";
    String pass = "";
    Connection c;

    public Connection getConnect() throws SQLException {

        c = DriverManager.getConnection(url, user, pass);

        return c;
    }

}
