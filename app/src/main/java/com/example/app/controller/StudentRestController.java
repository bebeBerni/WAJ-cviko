package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.app.entity.Student;
import com.example.app.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private final StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }

}