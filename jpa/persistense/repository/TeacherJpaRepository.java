package com.chisw.mongoPresentation.jpa.persistense.repository;

import com.chisw.mongoPresentation.jpa.persistense.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherJpaRepository extends JpaRepository<Teacher, Integer> {
}
