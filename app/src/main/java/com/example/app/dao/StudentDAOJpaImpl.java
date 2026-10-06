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
}
