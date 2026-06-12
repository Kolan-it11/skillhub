package com.skillhub.skillhub.service;

import com.skillhub.skillhub.entity.Assignment;
import com.skillhub.skillhub.entity.Submission;
import com.skillhub.skillhub.entity.User;
import com.skillhub.skillhub.repository.AssignmentRepository;
import com.skillhub.skillhub.repository.SubmissionRepository;
import com.skillhub.skillhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final AssignmentRepository assignmentRepository;

    public List<Submission> findAll() {
        return submissionRepository.findAll();
    }

    public Submission findById(Long id) {
        return submissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
    }

    public Submission submit(Long studentId, Long assignmentId, Submission submission) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        submission.setStudent(student);
        submission.setAssignment(assignment);
        submission.setSubmittedAt(LocalDateTime.now());
        submission.setGrade("PENDING");
        return submissionRepository.save(submission);
    }

    public Submission update(Long id, String grade) {
        Submission submission = findById(id);
        submission.setGrade(grade);
        return submissionRepository.save(submission);
    }

    public void delete(Long id) {
        submissionRepository.deleteById(id);
    }
}