package com.yerenmiras.studentapi.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByLastNameIgnoreCase(String lastName);
}
