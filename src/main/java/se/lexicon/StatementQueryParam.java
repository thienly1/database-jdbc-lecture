package se.lexicon;

import se.lexicon.model.Student;

import java.sql.*;

public class StatementQueryParam {

    public static final String URL = "jdbc:mysql://localhost:3306/jdbc_lecture?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Berlin";
    public static final String User = "root";
    public static final String Password = "123456";

    public static Student getStudentById(int id){
        // SELECT * FROM person WHERE id =5
        String findByIdQuery = "SELECT *FROM student where id= " + id;
        Student student = new Student();

        try{
            Connection connection = DriverManager.getConnection(URL, User, Password);
            Statement statement = connection.createStatement();
            ResultSet resultSet= statement.executeQuery(findByIdQuery);
            while(resultSet.next()){
                student.setId(resultSet.getInt(1));
                student.setFirstName(resultSet.getString(2));
                student.setLastName(resultSet.getString(3));
                student.setPersonalNumber(resultSet.getBigDecimal(4));
                student.setAddress(resultSet.getString(5));
                student.setRegisterDate(resultSet.getDate(6).toLocalDate());
                student.setStatus(resultSet.getBoolean(7));
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return student;
    }

    public static int deleteStudentById(int id){
        String delete_by_student_id_query = "DELETE FROM Student WHERE id = " + id;
        int rowAffective = 0;
        try{
            Connection connection = DriverManager.getConnection(URL,User,Password);
            Statement statement = connection.createStatement();
            rowAffective= statement.executeUpdate(delete_by_student_id_query);

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return rowAffective;
    }

    public static void main(String[] args) {
        System.out.println(getStudentById(5));
        System.out.println(deleteStudentById(9));

    }
}
