package se.lexicon.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
    public static final String URL = "jdbc:mysql://localhost:3306/jdbc_lecture?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Berlin";
    public static final String USER = "root";
    public static final String PASSWORD = "123456";

    public static Connection myCollection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL,USER, PASSWORD);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return connection;
    }
}
