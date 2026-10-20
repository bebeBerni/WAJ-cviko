
package com.example.app.service;

import com.example.app.exception.EmailAlreadyExistsException;
import com.example.app.exception.ObjectNotFoundException;
import com.example.app.repository.StudentRepository;
import com.example.app.entity.Student;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        return studentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Študent", id));
    }

    @Override
    public Student findById(int id) {
        return null;
    }

    @Transactional
    @Override
    public Student save(Student student) {
        // Kontrola duplicitného emailu pri vytváraní nového študenta
        if (student.getId() == null) {
            if (studentRepository.existsByEmail(student.getEmail())) {
                throw new EmailAlreadyExistsException(student.getEmail());
            }
        } else {
            // Pri aktualizácii skontroluje, či email nepatrí inému študentovi
            Student existingWithEmail = (Student) studentRepository.findByEmail(student.getEmail()).orElse(null);
            if (existingWithEmail != null && !Objects.equals(existingWithEmail.getId(), student.getId())) {
                throw new EmailAlreadyExistsException(student.getEmail());
            }
        }

        return studentRepository.save(student);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        // Skontroluje, či študent existuje
        if (!studentRepository.existsById(id)) {
            throw new ObjectNotFoundException("Študent", id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> findByFirstNameAndLastName(String firstName, String lastName) {
        return studentRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<Student> getSortedStudents() {
        return studentRepository.findAllByOrderByLastNameAsc();
    }
}