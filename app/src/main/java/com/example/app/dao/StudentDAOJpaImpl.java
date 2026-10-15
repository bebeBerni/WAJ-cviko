package com.example.app.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.app.entity.Student;

import java.util.List;

@Repository
public class StudentDAOJpaImpl implements StudentDAO {
    private final EntityManager entityManager;

    @Autowired
    public StudentDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("from Student", Student.class);
        List<Student> students = query.getResultList();
        return students;
    }

    @Override
    public Student findById(Integer id) {
        Student student = entityManager.find(Student.class, id);
        return student;
    }

    @Override
    public Student save(Student student) {
        Student savedStudent = entityManager.merge(student);
        return savedStudent;
    }

    @Override
    public void deleteById(Integer id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }

    @Override
    public List<Student> findByFirstNameAndLastName(String firstName, String lastName) {
        TypedQuery<Student> query = entityManager.createQuery("from Student where firstName=:firstName and lastName=:lastName", Student.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        List<Student> students = query.getResultList();
        return students;
    }

}
