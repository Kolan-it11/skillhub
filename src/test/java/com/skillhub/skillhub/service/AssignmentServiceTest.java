package com.skillhub.skillhub.service;

import com.skillhub.skillhub.entity.Assignment;
import com.skillhub.skillhub.entity.Course;
import com.skillhub.skillhub.repository.AssignmentRepository;
import com.skillhub.skillhub.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssignmentServiceTest {

    @Mock
    private AssignmentRepository assignmentRepository;
    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private AssignmentService assignmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        when(assignmentRepository.findAll()).thenReturn(List.of(new Assignment()));
        assertEquals(1, assignmentService.findAll().size());
    }

    @Test
    void testFindById() {
        Assignment assignment = new Assignment();
        when(assignmentRepository.findById(1L)).thenReturn(Optional.of(assignment));
        assertEquals(assignment, assignmentService.findById(1L));
    }

    @Test
    void testFindByIdNotFound() {
        when(assignmentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> assignmentService.findById(1L));
    }

    @Test
    void testCreate() {
        Course course = new Course();
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(assignmentRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Assignment result = assignmentService.create(1L, new Assignment());
        assertEquals(course, result.getCourse());
    }

    @Test
    void testCreateCourseNotFound() {
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> assignmentService.create(1L, new Assignment()));
    }

    @Test
    void testUpdate() {
        Assignment existing = new Assignment();
        existing.setTitle("Old");

        Assignment updated = new Assignment();
        updated.setTitle("New");
        updated.setDescription("NewDesc");

        when(assignmentRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(assignmentRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Assignment result = assignmentService.update(1L, updated);
        assertEquals("New", result.getTitle());
    }

    @Test
    void testDelete() {
        assignmentService.delete(1L);
        verify(assignmentRepository).deleteById(1L);
    }
}