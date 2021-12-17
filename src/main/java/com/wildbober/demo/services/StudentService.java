package com.wildbober.demo.services;

import com.wildbober.demo.DAO.StudentDao;
import com.wildbober.demo.model.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentService {

    public StudentService(){

    }
    public static Student findStudent(int id) throws SQLException {
        return StudentDao.getById(id);
    }

    public List<Student> findAllStudents() throws SQLException {
        return StudentDao.list();
    }

    public static void saveStudent(Student student) throws SQLException {
        StudentDao.addStudent(student);
    }

    public static void deleteStudent(int id) {
        StudentDao.deleteStudent(id);
    }

    public static void updateStudent(Student student, int id) {
        StudentDao.updateStudent(student, id);
    }
}
