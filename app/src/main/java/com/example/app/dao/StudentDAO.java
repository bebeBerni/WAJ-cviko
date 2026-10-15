package com.example.app.dao;

import com.example.app.entity.Student;
import java.util.List;

public interface StudentDAO {
    List<Student> findAll();

    Student findById(Integer id);

    Student save(Student student);

    void deleteById(Integer id);

    List<Student> findByFirstNameAndLastName(String firstName, String lastName);
}
