package com.skillhub.skillhub.repository;

import com.skillhub.skillhub.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}