package com.skillhub.skillhub.controller;

import com.skillhub.skillhub.entity.Certificate;
import com.skillhub.skillhub.service.CertificateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CertificateControllerTest {

    @Mock
    private CertificateService certificateService;

    @InjectMocks
    private CertificateController certificateController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        when(certificateService.findAll())
                .thenReturn(List.of(new Certificate()));

        List<Certificate> result = certificateController.getAll();

        assertEquals(1, result.size());
    }

    @Test
    void testGetById() {
        Certificate certificate = new Certificate();

        when(certificateService.findById(1L))
                .thenReturn(certificate);

        Certificate result = certificateController.getById(1L);

        assertEquals(certificate, result);
    }

    @Test
    void testIssue() {
        Certificate certificate = new Certificate();

        when(certificateService.issue(1L, 2L))
                .thenReturn(certificate);

        Certificate result = certificateController.issue(1L, 2L);

        assertNotNull(result);
    }

    @Test
    void testUpdate() {
        Certificate certificate = new Certificate();

        when(certificateService.update(eq(1L), any(Certificate.class)))
                .thenReturn(certificate);

        Certificate result = certificateController.update(1L, new Certificate());

        assertNotNull(result);
    }

    @Test
    void testDelete() {
        certificateController.delete(1L);

        verify(certificateService).delete(1L);
    }
}