package com.skillhub.skillhub.service;

import com.skillhub.skillhub.entity.Course;
import com.skillhub.skillhub.entity.Enrollment;
import com.skillhub.skillhub.entity.User;
import com.skillhub.skillhub.repository.CourseRepository;
import com.skillhub.skillhub.repository.EnrollmentRepository;
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

class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        when(enrollmentRepository.findAll()).thenReturn(List.of(new Enrollment()));
        assertEquals(1, enrollmentService.findAll().size());
    }

    @Test
    void testFindById() {
        Enrollment enrollment = new Enrollment();
        when(enrollmentRepository.findById(1L)).thenReturn(Optional.of(enrollment));
        assertEquals(enrollment, enrollmentService.findById(1L));
    }

    @Test
    void testFindByIdNotFound() {
        when(enrollmentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> enrollmentService.findById(1L));
    }

    @Test
    void testEnroll() {
        User student = new User();
        Course course = new Course();

        when(userRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepository.findById(2L)).thenReturn(Optional.of(course));
        when(enrollmentRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Enrollment result = enrollmentService.enroll(1L, 2L);
        assertEquals("ACTIVE", result.getStatus());
        assertEquals(student, result.getStudent());
    }

    @Test
    void testEnrollStudentNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> enrollmentService.enroll(1L, 2L));
    }

    @Test
    void testEnrollCourseNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        when(courseRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> enrollmentService.enroll(1L, 2L));
    }

    @Test
    void testUpdate() {
        Enrollment enrollment = new Enrollment();
        enrollment.setStatus("ACTIVE");

        when(enrollmentRepository.findById(1L)).thenReturn(Optional.of(enrollment));
        when(enrollmentRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Enrollment result = enrollmentService.update(1L, "COMPLETED");
        assertEquals("COMPLETED", result.getStatus());
    }

    @Test
    void testDelete() {
        enrollmentService.delete(1L);
        verify(enrollmentRepository).deleteById(1L);
    }
}