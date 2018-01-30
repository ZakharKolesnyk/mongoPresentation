package com.chisw.mongoPresentation.jpa.persistense.repository;

import com.chisw.mongoPresentation.jpa.persistense.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupJpaRepository extends JpaRepository<Group, Integer> {
}
