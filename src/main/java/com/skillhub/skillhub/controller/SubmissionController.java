package com.skillhub.skillhub.controller;

import com.skillhub.skillhub.entity.Submission;
import com.skillhub.skillhub.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @GetMapping
    public List<Submission> getAll() {
        return submissionService.findAll();
    }

    @GetMapping("/{id}")
    public Submission getById(@PathVariable Long id) {
        return submissionService.findById(id);
    }

    @PostMapping
    public Submission submit(@RequestParam Long studentId,
                             @RequestParam Long assignmentId,
                             @RequestBody Submission submission) {
        return submissionService.submit(studentId, assignmentId, submission);
    }

    @PutMapping("/{id}")
    public Submission update(@PathVariable Long id,
                             @RequestParam String grade) {
        return submissionService.update(id, grade);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        submissionService.delete(id);
    }
}