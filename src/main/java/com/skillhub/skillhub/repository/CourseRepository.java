package com.skillhub.skillhub.repository;

import com.skillhub.skillhub.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}