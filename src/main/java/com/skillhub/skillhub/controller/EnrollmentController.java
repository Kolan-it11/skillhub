package com.skillhub.skillhub.controller;

import com.skillhub.skillhub.entity.Enrollment;
import com.skillhub.skillhub.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @GetMapping
    public List<Enrollment> getAll() {
        return enrollmentService.findAll();
    }

    @GetMapping("/{id}")
    public Enrollment getById(@PathVariable Long id) {
        return enrollmentService.findById(id);
    }

    @PostMapping
    public Enrollment enroll(@RequestParam Long studentId,
                             @RequestParam Long courseId) {
        return enrollmentService.enroll(studentId, courseId);
    }

    @PutMapping("/{id}")
    public Enrollment update(@PathVariable Long id,
                             @RequestParam String status) {
        return enrollmentService.update(id, status);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        enrollmentService.delete(id);
    }
}