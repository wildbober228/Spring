package com.wildbober.demo.controller;

import com.wildbober.demo.model.Student;
import com.wildbober.demo.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private static StudentService studentService = new StudentService();
    @GetMapping
    public List<Student> getStudents() throws SQLException {
        return studentService.findAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) throws SQLException {
        return studentService.findStudent(id);
    }

    @PostMapping("/create")
    public ResponseEntity createStudent(@RequestBody Student student) throws URISyntaxException, SQLException {
        studentService.saveStudent(student);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateStudent(@PathVariable int id, @RequestBody Student student) throws SQLException {
        studentService.updateStudent(student, id);
        return ResponseEntity.ok("student");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

}
