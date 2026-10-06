package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.app.dao.StudentDAO;
import com.example.app.entity.Student;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDAO studentDAO;

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }
}