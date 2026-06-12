package com.skillhub.skillhub.controller;

import com.skillhub.skillhub.entity.Course;
import com.skillhub.skillhub.entity.Lesson;
import com.skillhub.skillhub.repository.CourseRepository;
import com.skillhub.skillhub.service.LessonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LessonControllerTest {

    @Mock
    private LessonService lessonService;
    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private LessonController lessonController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        when(lessonService.findAll()).thenReturn(List.of(new Lesson()));
        assertEquals(1, lessonController.getAll().size());
    }

    @Test
    void testGetById() {
        Lesson lesson = new Lesson();
        when(lessonService.findById(1L)).thenReturn(lesson);
        assertEquals(lesson, lessonController.getById(1L));
    }

    @Test
    void testCreate() {
        Course course = new Course();
        Lesson lesson = new Lesson();

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(lessonService.save(any())).thenReturn(lesson);

        Lesson result = lessonController.create(new Lesson(), 1L);
        assertNotNull(result);
    }

    @Test
    void testCreateCourseNotFound() {
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> lessonController.create(new Lesson(), 1L));
    }

    @Test
    void testUpdate() {
        Lesson lesson = new Lesson();
        when(lessonService.update(eq(1L), any())).thenReturn(lesson);
        assertEquals(lesson, lessonController.update(1L, lesson));
    }

    @Test
    void testDelete() {
        lessonController.delete(1L);
        verify(lessonService).delete(1L);
    }
}