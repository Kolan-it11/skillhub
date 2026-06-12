package com.skillhub.skillhub.service;

import com.skillhub.skillhub.entity.Assignment;
import com.skillhub.skillhub.entity.Course;
import com.skillhub.skillhub.repository.AssignmentRepository;
import com.skillhub.skillhub.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final CourseRepository courseRepository;

    public List<Assignment> findAll() {
        return assignmentRepository.findAll();
    }

    public Assignment findById(Long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
    }

    public Assignment create(Long courseId, Assignment assignment) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        assignment.setCourse(course);
        return assignmentRepository.save(assignment);
    }

    public Assignment update(Long id, Assignment updated) {
        Assignment assignment = findById(id);
        assignment.setTitle(updated.getTitle());
        assignment.setDescription(updated.getDescription());
        assignment.setDeadline(updated.getDeadline());
        return assignmentRepository.save(assignment);
    }

    public void delete(Long id) {
        assignmentRepository.deleteById(id);
    }
}