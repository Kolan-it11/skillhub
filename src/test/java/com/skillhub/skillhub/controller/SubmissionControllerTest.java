package com.skillhub.skillhub.controller;

import com.skillhub.skillhub.entity.Submission;
import com.skillhub.skillhub.service.SubmissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubmissionControllerTest {

    @Mock
    private SubmissionService submissionService;

    @InjectMocks
    private SubmissionController submissionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        when(submissionService.findAll()).thenReturn(List.of(new Submission()));
        assertEquals(1, submissionController.getAll().size());
    }

    @Test
    void testGetById() {
        Submission submission = new Submission();
        when(submissionService.findById(1L)).thenReturn(submission);
        assertEquals(submission, submissionController.getById(1L));
    }

    @Test
    void testSubmit() {
        Submission submission = new Submission();
        when(submissionService.submit(eq(1L), eq(2L), any())).thenReturn(submission);
        assertEquals(submission, submissionController.submit(1L, 2L, submission));
    }

    @Test
    void testUpdate() {
        Submission submission = new Submission();
        when(submissionService.update(eq(1L), eq("A"))).thenReturn(submission);
        assertEquals(submission, submissionController.update(1L, "A"));
    }

    @Test
    void testDelete() {
        submissionController.delete(1L);
        verify(submissionService).delete(1L);
    }
}