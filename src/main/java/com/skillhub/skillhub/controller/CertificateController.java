package com.skillhub.skillhub.controller;

import com.skillhub.skillhub.entity.Certificate;
import com.skillhub.skillhub.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certificates")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    @GetMapping
    public List<Certificate> getAll() {
        return certificateService.findAll();
    }

    @GetMapping("/{id}")
    public Certificate getById(@PathVariable Long id) {
        return certificateService.findById(id);
    }

    @PostMapping
    public Certificate issue(@RequestParam Long studentId,
                             @RequestParam Long courseId) {
        return certificateService.issue(studentId, courseId);
    }

    @PutMapping("/{id}")
    public Certificate update(@PathVariable Long id,
                              @RequestBody Certificate certificate) {
        return certificateService.update(id, certificate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        certificateService.delete(id);
    }
}