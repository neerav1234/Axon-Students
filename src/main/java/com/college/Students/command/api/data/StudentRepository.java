package com.college.Students.command.api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public interface StudentRepository extends JpaRepository<Student, String> {
}
