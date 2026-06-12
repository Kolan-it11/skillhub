package com.skillhub.skillhub.service;

import com.skillhub.skillhub.entity.Course;
import com.skillhub.skillhub.entity.User;
import com.skillhub.skillhub.repository.CourseRepository;
import com.skillhub.skillhub.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        when(courseRepository.findAll()).thenReturn(List.of(new Course()));
        assertEquals(1, courseService.findAll().size());
    }

    @Test
    void testFindById() {
        Course course = new Course();
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        assertEquals(course, courseService.findById(1L));
    }

    @Test
    void testFindByIdNotFound() {
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> courseService.findById(1L));
    }

    @Test
    void testSave() {
        User teacher = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(courseRepository.save(any(Course.class))).thenAnswer(inv -> inv.getArgument(0));

        Course result = courseService.save(new Course(), 1L);
        assertEquals(teacher, result.getTeacher());
    }

    @Test
    void testSaveTeacherNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> courseService.save(new Course(), 1L));
    }

    @Test
    void testUpdate() {
        Course existing = new Course();
        existing.setTitle("Old");
        existing.setDescription("OldDesc");

        Course updated = new Course();
        updated.setTitle("New");
        updated.setDescription("NewDesc");

        when(courseRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(courseRepository.save(any(Course.class))).thenAnswer(inv -> inv.getArgument(0));

        Course result = courseService.update(1L, updated);
        assertEquals("New", result.getTitle());
        assertEquals("NewDesc", result.getDescription());
    }

    @Test
    void testDelete() {
        courseService.delete(1L);
        verify(courseRepository).deleteById(1L);
    }
}