package com.skillhub.skillhub.repository;

import com.skillhub.skillhub.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}