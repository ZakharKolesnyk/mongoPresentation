package com.chisw.mongoPresentation.jpa.persistense.repository;

import com.chisw.mongoPresentation.jpa.persistense.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentJpaRepository extends JpaRepository<Student, Integer> {
}
