package com.example.studentapp.controller;

import com.example.studentapp.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    List<Student> students = new ArrayList<>();

    // GET API
    @GetMapping
    public List<Student> getStudents() {
        return students;
    }

    // POST API
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }
}