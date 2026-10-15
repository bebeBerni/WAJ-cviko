package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.app.entity.Student;
import com.example.app.service.StudentService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable Integer id) {

        Student student = studentService.findById(id);

        if (student == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student id not found - " + id);
        }

        return student;
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        student.setId(null);
        Student savedStudent = studentService.save(student);
        return savedStudent;
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        Student existingStudent = studentService.findById(id);
        if (existingStudent == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student id not found - " + id);
        }
        student.setId(id);
        Student updatedStudent = studentService.save(student);
        return updatedStudent;
    }

    @DeleteMapping("/students/{id}")
    public Map<String, String> deleteStudent(@PathVariable Integer id) {

        Student student = studentService.findById(id);

        if (student == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Student id not found - " + id
            );
        }

        studentService.deleteById(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Deleted student id - " + id);

        return response;
    }

    @GetMapping("/students/search")
    public List<Student> searchStudents(@RequestParam String firstName, @RequestParam String lastName) {
        return studentService.findByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping("/students/sorted")
    public List<Student> getSortedStudents() {
        return studentService.getSortedStudents();
    }

}