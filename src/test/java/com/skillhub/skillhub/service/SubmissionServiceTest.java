package com.skillhub.skillhub.service;

import com.skillhub.skillhub.entity.Assignment;
import com.skillhub.skillhub.entity.Submission;
import com.skillhub.skillhub.entity.User;
import com.skillhub.skillhub.repository.AssignmentRepository;
import com.skillhub.skillhub.repository.SubmissionRepository;
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

class SubmissionServiceTest {

    @Mock
    private SubmissionRepository submissionRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AssignmentRepository assignmentRepository;

    @InjectMocks
    private SubmissionService submissionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        when(submissionRepository.findAll()).thenReturn(List.of(new Submission()));
        assertEquals(1, submissionService.findAll().size());
    }

    @Test
    void testFindById() {
        Submission submission = new Submission();
        when(submissionRepository.findById(1L)).thenReturn(Optional.of(submission));
        assertEquals(submission, submissionService.findById(1L));
    }

    @Test
    void testFindByIdNotFound() {
        when(submissionRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> submissionService.findById(1L));
    }

    @Test
    void testSubmit() {
        User student = new User();
        Assignment assignment = new Assignment();
        Submission submission = new Submission();

        when(userRepository.findById(1L)).thenReturn(Optional.of(student));
        when(assignmentRepository.findById(2L)).thenReturn(Optional.of(assignment));
        when(submissionRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Submission result = submissionService.submit(1L, 2L, submission);
        assertEquals(student, result.getStudent());
        assertEquals("PENDING", result.getGrade());
    }

    @Test
    void testSubmitStudentNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> submissionService.submit(1L, 2L, new Submission()));
    }

    @Test
    void testSubmitAssignmentNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        when(assignmentRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> submissionService.submit(1L, 2L, new Submission()));
    }

    @Test
    void testUpdate() {
        Submission submission = new Submission();
        submission.setGrade("PENDING");

        when(submissionRepository.findById(1L)).thenReturn(Optional.of(submission));
        when(submissionRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Submission result = submissionService.update(1L, "A");
        assertEquals("A", result.getGrade());
    }

    @Test
    void testDelete() {
        submissionService.delete(1L);
        verify(submissionRepository).deleteById(1L);
    }
}