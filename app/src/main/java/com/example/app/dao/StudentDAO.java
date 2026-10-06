package com.example.app.dao;

import com.example.app.entity.Student;
import java.util.List;

public interface StudentDAO {
    List<Student> findAll();
}
