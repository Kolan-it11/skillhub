package com.skillhub.skillhub.controller;

import com.skillhub.skillhub.entity.Assignment;
import com.skillhub.skillhub.service.AssignmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssignmentControllerTest {

    @Mock
    private AssignmentService assignmentService;

    @InjectMocks
    private AssignmentController assignmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        when(assignmentService.findAll()).thenReturn(List.of(new Assignment()));
        assertEquals(1, assignmentController.getAll().size());
    }

    @Test
    void testGetById() {
        Assignment assignment = new Assignment();
        when(assignmentService.findById(1L)).thenReturn(assignment);
        assertEquals(assignment, assignmentController.getById(1L));
    }

    @Test
    void testCreate() {
        Assignment assignment = new Assignment();
        when(assignmentService.create(eq(1L), any())).thenReturn(assignment);
        assertEquals(assignment, assignmentController.create(1L, assignment));
    }

    @Test
    void testUpdate() {
        Assignment assignment = new Assignment();
        when(assignmentService.update(eq(1L), any())).thenReturn(assignment);
        assertEquals(assignment, assignmentController.update(1L, assignment));
    }

    @Test
    void testDelete() {
        assignmentController.delete(1L);
        verify(assignmentService).delete(1L);
    }
}