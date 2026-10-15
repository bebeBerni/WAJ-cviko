package com.example.app.service;

import com.example.app.entity.Student;
import jakarta.transaction.Transactional;

import java.util.List;

public interface StudentService {
    List<Student> findAll();

    Student findById(Integer id);

    @Transactional
    Student save(Student student);

    @Transactional
    void deleteById(Integer id);

    List<Student> findByFirstNameAndLastName(String firstName, String lastName);
}
