package com.skillhub.skillhub.repository;

import com.skillhub.skillhub.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}