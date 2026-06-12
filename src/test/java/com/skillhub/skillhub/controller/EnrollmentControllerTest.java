package com.skillhub.skillhub.controller;

import com.skillhub.skillhub.entity.Enrollment;
import com.skillhub.skillhub.service.EnrollmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnrollmentControllerTest {

    @Mock
    private EnrollmentService enrollmentService;

    @InjectMocks
    private EnrollmentController enrollmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        when(enrollmentService.findAll()).thenReturn(List.of(new Enrollment()));
        assertEquals(1, enrollmentController.getAll().size());
    }

    @Test
    void testGetById() {
        Enrollment enrollment = new Enrollment();
        when(enrollmentService.findById(1L)).thenReturn(enrollment);
        assertEquals(enrollment, enrollmentController.getById(1L));
    }

    @Test
    void testEnroll() {
        Enrollment enrollment = new Enrollment();
        when(enrollmentService.enroll(1L, 2L)).thenReturn(enrollment);
        assertEquals(enrollment, enrollmentController.enroll(1L, 2L));
    }

    @Test
    void testUpdate() {
        Enrollment enrollment = new Enrollment();
        when(enrollmentService.update(eq(1L), eq("COMPLETED"))).thenReturn(enrollment);
        assertEquals(enrollment, enrollmentController.update(1L, "COMPLETED"));
    }

    @Test
    void testDelete() {
        enrollmentController.delete(1L);
        verify(enrollmentService).delete(1L);
    }
}