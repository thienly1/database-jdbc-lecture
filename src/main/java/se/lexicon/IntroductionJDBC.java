package se.lexicon;

import java.sql.*;

public class IntroductionJDBC {
    public static void main(String[] args) {
        try{
            Connection connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/jdbc_lecture?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Berlin"
                    ,"root",
                    "123456");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM student");

            while(resultSet.next()){
                System.out.print(" | id = " + resultSet.getInt("id"));
                System.out.print(" | first_name = " + resultSet.getString("first_name"));
                System.out.print(" | last_name = " + resultSet.getString("last_name"));
                System.out.print(" | personal_number = " + resultSet.getBigDecimal("personal_number"));
                System.out.print(" | address = " + resultSet.getString("address"));
                System.out.print(" | registration_date = " + resultSet.getTimestamp("registration_date"));
                System.out.print(" | status = " + resultSet.getBoolean("status"));
                System.out.println();;
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage()); // or ex.printStackTrace
        }

    }
}
