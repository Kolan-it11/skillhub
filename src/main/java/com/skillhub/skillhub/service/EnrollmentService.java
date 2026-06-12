package com.skillhub.skillhub.service;

import com.skillhub.skillhub.entity.Course;
import com.skillhub.skillhub.entity.Enrollment;
import com.skillhub.skillhub.entity.User;
import com.skillhub.skillhub.repository.CourseRepository;
import com.skillhub.skillhub.repository.EnrollmentRepository;
import com.skillhub.skillhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    public Enrollment findById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
    }

    public Enrollment enroll(Long studentId, Long courseId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .enrolledAt(LocalDateTime.now())
                .status("ACTIVE")
                .build();
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment update(Long id, String status) {
        Enrollment enrollment = findById(id);
        enrollment.setStatus(status);
        return enrollmentRepository.save(enrollment);
    }

    public void delete(Long id) {
        enrollmentRepository.deleteById(id);
    }
}