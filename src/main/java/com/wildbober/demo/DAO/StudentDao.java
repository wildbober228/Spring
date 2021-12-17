package com.wildbober.demo.DAO;

import com.wildbober.demo.model.Student;
import com.wildbober.demo.utils.ConnectionData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    public static void addStudent(Student student){
        try {
            Connection con = DriverManager.getConnection(ConnectionData.URL, ConnectionData.USER, ConnectionData.PASS);
            String sql = "INSERT INTO "+ConnectionData.NAME_DATABASE+".students (id,firstName,secondName,age) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, 0);
            pst.setString(2, student.getFirstName());
            pst.setString(3, student.getSecondName());
            pst.setInt(4, student.getStudentAge());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateStudent(Student student, int id){
        try{
            Connection con = DriverManager.getConnection(ConnectionData.URL, ConnectionData.USER, ConnectionData.PASS);
            String sql = "UPDATE "+ConnectionData.NAME_DATABASE+".students SET firstName = ? , secondName = ? , age = ? WHERE id = ?" ;
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, student.getFirstName());
            pst.setString(2,  student.getSecondName());
            pst.setInt(3, student.getStudentAge());
            pst.setInt(4, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteStudent(int id){
        try {
            Connection con = DriverManager.getConnection(ConnectionData.URL, ConnectionData.USER, ConnectionData.PASS);
            String sql = "DELETE FROM "+ConnectionData.NAME_DATABASE+".students WHERE "+ConnectionData.NAME_DATABASE+".students.id = "+id;
            PreparedStatement pst = con.prepareStatement(sql);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static List<Student> list() throws SQLException {
        List<Student> listStudent = new ArrayList<Student>();
        try (Connection connection = DriverManager.getConnection(ConnectionData.URL, ConnectionData.USER, ConnectionData.PASS)) {
            String sql = "SELECT * FROM "+ConnectionData.NAME_DATABASE+".students";

            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery(sql);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int column_count = rsmd.getColumnCount();
            while (resultSet.next()) {
                int id = resultSet.getInt(1    );
                String firstName = resultSet.getString(2);
                String secondName = resultSet.getString(3);
                int studentAge = resultSet.getInt(4);
                Student student = new Student(id,firstName,secondName,studentAge);
                listStudent.add(student);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }

        return listStudent;
    }

    public static Student getById(int _id) throws SQLException {
        Student student = null;
        try (Connection connection = DriverManager.getConnection(ConnectionData.URL, ConnectionData.USER, ConnectionData.PASS)) {
            String sql = "SELECT * FROM "+ConnectionData.NAME_DATABASE+".students WHERE id = "+_id;
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                int id = result.getInt(1    );
                String firstName = result.getString(2);
                String secondName = result.getString(3);
                int studentAge = result.getInt(4);
                student = new Student(id, firstName, secondName,studentAge);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return student;
    }


}
