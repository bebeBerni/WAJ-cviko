package com.example.app.repository;

import com.example.app.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByFirstNameAndLastName(
            String firstName,
            String lastName
    );

    List<Student> findAllByOrderByLastNameAsc();

    boolean existsByEmail(String email);

    Optional<Student> findByEmail(String email);
}