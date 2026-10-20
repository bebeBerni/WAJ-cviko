package com.example.app.controller;

import com.example.app.response.ResponseFactory;
import com.example.app.response.ResponseWrapper;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.app.entity.Student;
import com.example.app.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private final StudentService studentService;
    private final ResponseFactory response;

    @Autowired
    public StudentRestController(
            StudentService studentService,
            ResponseFactory response) {

        this.studentService = studentService;
        this.response = response;
    }

    // 1. Získanie všetkých študentov
    @GetMapping("/students")
    public ResponseEntity<ResponseWrapper<List<Student>>> getAllStudents() {

        List<Student> students = studentService.findAll();

        return response.ok("Študenti úspešne načítaní", students);
    }

    // 2. Získanie študenta podľa ID
    @GetMapping("/students/{id}")
    public ResponseEntity<ResponseWrapper<Student>> getStudentById(
            @PathVariable Integer id) {

        Student student = studentService.findById(id);

        if (student == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Študent s ID " + id + " neexistuje"
            );
        }

        return response.ok("Študent úspešne načítaný", student);
    }

    // 3. Vytvorenie nového študenta
    @PostMapping("/students")
    public ResponseEntity<ResponseWrapper<Student>> createStudent(
            @Valid @RequestBody Student student) {

        student.setId(null);

        Student savedStudent = studentService.save(student);

        ResponseEntity<ResponseWrapper<Student>> result =
                response.ok("Študent úspešne vytvorený", savedStudent);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result.getBody());
    }

    // 4. Aktualizácia existujúceho študenta
    @PutMapping("/students/{id}")
    public ResponseEntity<ResponseWrapper<Student>> updateStudent(
            @PathVariable Integer id,
            @Valid @RequestBody Student student) {

        Student existingStudent = studentService.findById(id);

        if (existingStudent == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Študent s ID " + id + " neexistuje"
            );
        }

        student.setId(id);

        Student updatedStudent = studentService.save(student);

        return response.ok(
                "Študent úspešne aktualizovaný",
                updatedStudent
        );
    }

    // 5. Odstránenie študenta
    @DeleteMapping("/students/{id}")
    public ResponseEntity<ResponseWrapper<Student>> deleteStudent(
            @PathVariable Integer id) {

        Student student = studentService.findById(id);

        if (student == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Študent s ID " + id + " neexistuje"
            );
        }

        studentService.deleteById(id);

        return response.ok("Študent úspešne odstránený", student);
    }

    // 6. Vyhľadávanie podľa mena a priezviska
    @GetMapping("/students/search")
    public ResponseEntity<ResponseWrapper<List<Student>>> searchStudents(
            @RequestParam String firstName,
            @RequestParam String lastName) {

        List<Student> students =
                studentService.findByFirstNameAndLastName(
                        firstName, lastName
                );

        return response.ok(
                "Výsledky vyhľadávania úspešne načítané",
                students
        );
    }

    // 7. Zoradenie študentov podľa priezviska
    @GetMapping("/students/sorted")
    public ResponseEntity<ResponseWrapper<List<Student>>> getSortedStudents() {

        List<Student> students = studentService.getSortedStudents();

        return response.ok(
                "Študenti úspešne zoradení podľa priezviska",
                students
        );
    }
}