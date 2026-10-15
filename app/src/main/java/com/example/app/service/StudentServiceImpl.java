
package com.example.app.service;

import com.example.app.repository.StudentRepository;
import com.example.app.entity.Student;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student findById(int id) {
        return findById(Integer.valueOf(id));
    }

    @Transactional
    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        deleteById(Integer.valueOf(id));
    }

    @Override
    public List<Student> findByFirstNameAndLastName(
            String firstName, String lastName) {

        return studentRepository
                .findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Student> getSortedStudents() {
        return studentRepository.findAllByOrderByLastNameAsc();
    }
}
