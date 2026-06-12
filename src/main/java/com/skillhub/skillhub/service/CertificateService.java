package com.skillhub.skillhub.service;

import com.skillhub.skillhub.entity.Certificate;
import com.skillhub.skillhub.entity.Course;
import com.skillhub.skillhub.entity.User;
import com.skillhub.skillhub.repository.CertificateRepository;
import com.skillhub.skillhub.repository.CourseRepository;
import com.skillhub.skillhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CertificateService {

    private final CertificateRepository certificateRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public List<Certificate> findAll() {
        return certificateRepository.findAll();
    }

    public Certificate findById(Long id) {
        return certificateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }

    public Certificate issue(Long studentId, Long courseId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Certificate certificate = Certificate.builder()
                .student(student)
                .course(course)
                .issuedAt(LocalDateTime.now())
                .certificateNumber(UUID.randomUUID().toString())
                .build();

        return certificateRepository.save(certificate);
    }

    public Certificate update(Long id, Certificate updated) {
        Certificate certificate = findById(id);

        if (updated.getIssuedAt() != null) {
            certificate.setIssuedAt(updated.getIssuedAt());
        }

        if (updated.getCertificateNumber() != null) {
            certificate.setCertificateNumber(updated.getCertificateNumber());
        }

        return certificateRepository.save(certificate);
    }

    public void delete(Long id) {
        certificateRepository.deleteById(id);
    }
}