package com.skillhub.skillhub.controller;

import com.skillhub.skillhub.entity.Course;
import com.skillhub.skillhub.entity.Lesson;
import com.skillhub.skillhub.repository.CourseRepository;
import com.skillhub.skillhub.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;
    private final CourseRepository courseRepository;

    @GetMapping
    public List<Lesson> getAll() {
        return lessonService.findAll();
    }

    @GetMapping("/{id}")
    public Lesson getById(@PathVariable Long id) {
        return lessonService.findById(id);
    }

    @PostMapping
    public Lesson create(@RequestBody Lesson lesson,
                         @RequestParam Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        lesson.setCourse(course);
        return lessonService.save(lesson);
    }

    @PutMapping("/{id}")
    public Lesson update(@PathVariable Long id,
                         @RequestBody Lesson lesson) {
        return lessonService.update(id, lesson);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        lessonService.delete(id);
    }
}