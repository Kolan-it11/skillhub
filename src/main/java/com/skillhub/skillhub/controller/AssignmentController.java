package com.skillhub.skillhub.controller;

import com.skillhub.skillhub.entity.Assignment;
import com.skillhub.skillhub.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @GetMapping
    public List<Assignment> getAll() {
        return assignmentService.findAll();
    }

    @GetMapping("/{id}")
    public Assignment getById(@PathVariable Long id) {
        return assignmentService.findById(id);
    }

    @PostMapping
    public Assignment create(@RequestParam Long courseId,
                             @RequestBody Assignment assignment) {
        return assignmentService.create(courseId, assignment);
    }

    @PutMapping("/{id}")
    public Assignment update(@PathVariable Long id,
                             @RequestBody Assignment assignment) {
        return assignmentService.update(id, assignment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        assignmentService.delete(id);
    }
}