package com.skillhub.skillhub.repository;

import com.skillhub.skillhub.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}