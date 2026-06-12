package com.skillhub.skillhub.controller;

import com.skillhub.skillhub.entity.Course;
import com.skillhub.skillhub.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        when(courseService.findAll()).thenReturn(List.of(new Course()));
        assertEquals(1, courseController.getAll().size());
    }

    @Test
    void testGetById() {
        Course course = new Course();
        when(courseService.findById(1L)).thenReturn(course);
        assertEquals(course, courseController.getById(1L));
    }

    @Test
    void testCreate() {
        Course course = new Course();
        when(courseService.save(any(), eq(1L))).thenReturn(course);
        assertEquals(course, courseController.create(course, 1L));
    }

    @Test
    void testUpdate() {
        Course course = new Course();
        when(courseService.update(eq(1L), any())).thenReturn(course);
        assertEquals(course, courseController.update(1L, course));
    }

    @Test
    void testDelete() {
        courseController.delete(1L);
        verify(courseService).delete(1L);
    }
}