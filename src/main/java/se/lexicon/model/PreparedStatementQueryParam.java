package se.lexicon.model;

import com.mysql.cj.MysqlConnection;

import java.sql.*;
import java.time.LocalDate;

public class PreparedStatementQueryParam {

    public static Student getStudentById(int id){
        // SELECT *FROM student where id= 5
        String findByIdQuery = "SELECT * FROM student where id= ?";
        Student student = new Student();
        try{
            Connection connection = MySqlConnection.myCollection();
            PreparedStatement preparedStatement = connection.prepareStatement(findByIdQuery);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                student.setId(resultSet.getInt(1));
                student.setFirstName(resultSet.getString(2));
                student.setLastName(resultSet.getString(3));
                student.setPersonalNumber(resultSet.getBigDecimal(4));
                student.setAddress(resultSet.getString(5));
                student.setRegisterDate(LocalDate.parse(resultSet.getDate(6).toString()));
                student.setStatus(resultSet.getBoolean(7));
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return student;
    }
    public static int deleteStudentByIdAndName(int id, String name){
        int rowsAffected = 0;
        String DELETE_QUERY = "DELETE FROM student WHERE id = ? AND first_name LIKE ?";

        try{
            Connection connection= MySqlConnection.myCollection();
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            //DELETE FROM student WHERE id= ? AND first_name LIKE ?
            statement.setInt(1, id);
            // DELETE FROM student WHERE id= 1000 AND first_name LIKE ?
            statement.setString(2, name);
            // DELETE FROM student WHERE id = 1000 AND first_name = Simon

            rowsAffected = statement.executeUpdate();

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return rowsAffected;
    }

    public static void main(String[] args) {
        System.out.println(getStudentById(14));
        System.out.println(deleteStudentByIdAndName(10, "Odi"));

    }
}
