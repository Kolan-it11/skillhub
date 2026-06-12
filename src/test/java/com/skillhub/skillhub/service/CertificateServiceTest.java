package com.skillhub.skillhub.service;

import com.skillhub.skillhub.entity.Certificate;
import com.skillhub.skillhub.entity.Course;
import com.skillhub.skillhub.entity.User;
import com.skillhub.skillhub.repository.CertificateRepository;
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

class CertificateServiceTest {

    @Mock
    private CertificateRepository certificateRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CertificateService certificateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        when(certificateRepository.findAll()).thenReturn(List.of(new Certificate()));
        assertEquals(1, certificateService.findAll().size());
    }

    @Test
    void testFindById() {
        Certificate cert = new Certificate();
        when(certificateRepository.findById(1L)).thenReturn(Optional.of(cert));
        assertEquals(cert, certificateService.findById(1L));
    }

    @Test
    void testFindByIdNotFound() {
        when(certificateRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> certificateService.findById(1L));
    }

    @Test
    void testIssue() {
        User student = new User();
        Course course = new Course();

        when(userRepository.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepository.findById(2L)).thenReturn(Optional.of(course));
        when(certificateRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Certificate result = certificateService.issue(1L, 2L);
        assertEquals(student, result.getStudent());
        assertEquals(course, result.getCourse());
        assertNotNull(result.getCertificateNumber());
    }

    @Test
    void testIssueStudentNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> certificateService.issue(1L, 2L));
    }

    @Test
    void testIssueCourseNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        when(courseRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> certificateService.issue(1L, 2L));
    }

    @Test
    void testDelete() {
        certificateService.delete(1L);
        verify(certificateRepository).deleteById(1L);
    }
}