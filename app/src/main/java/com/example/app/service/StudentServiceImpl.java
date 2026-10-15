package com.example.app.service;

import org.springframework.transaction.annotation.Transactional;
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
    @Override
    public Student findById(Integer id) {
        return studentDAO.findById(id);
    }
    @Transactional
    @Override
    public Student save(Student student) {
        return studentDAO.save(student);
    }
    @Transactional
    @Override
    public void deleteById(Integer id) {
        studentDAO.deleteById(id);
    }

    @Override
    public List<Student> findByFirstNameAndLastName(String firstName, String lastName) {
        return studentDAO.findByFirstNameAndLastName(firstName, lastName);
    }

}