package com.wildbober.demo.controller;

import com.wildbober.demo.model.Student;
import com.wildbober.demo.services.StudentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private static StudentService studentService = new StudentService();

    @GetMapping(headers = "Accept=application/json")
    public ResponseEntity<List<Student>> getListPost(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "25") Integer size) throws SQLException {
        ArrayList<Student> returnList = new ArrayList<Student>();
        int length = studentService.findAllStudents().size();
        for (int i = (size * page) - size; i < size * page; i ++) {
            if(i < length) {
                returnList.add(studentService.findAllStudents().get(i));
            }
        }

        HttpHeaders headers = new HttpHeaders() {
            {
                add("Access-Control-Expose-Headers", "Content-Range");
                add("Content-Range", String.valueOf(length));
            }
        };
        return new ResponseEntity<List<Student>>(returnList, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) throws SQLException {
        System.out.println("GET ID = "+ id);
        System.out.println("STUDENT  "+ studentService.findStudent(id).getSecondName());
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
