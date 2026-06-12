package com.skillhub.skillhub.service;

import com.skillhub.skillhub.entity.Course;
import com.skillhub.skillhub.entity.User;
import com.skillhub.skillhub.repository.CourseRepository;
import com.skillhub.skillhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public Course save(Course course, Long teacherId) {
        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        course.setTeacher(teacher);
        return courseRepository.save(course);
    }

    public Course update(Long id, Course updated) {
        Course course = findById(id);
        course.setTitle(updated.getTitle());
        course.setDescription(updated.getDescription());
        return courseRepository.save(course);
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}