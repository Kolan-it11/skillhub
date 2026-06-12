package com.skillhub.skillhub.controller;

import com.skillhub.skillhub.entity.User;
import com.skillhub.skillhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/students")
    public User createStudent(@RequestBody User user) {
        return userService.createStudent(user);
    }

    @PostMapping("/teachers")
    public User createTeacher(@RequestBody User user) {
        return userService.createTeacher(user);
    }

    @PostMapping("/admins")
    public User createAdmin(@RequestBody User user) {
        return userService.createAdmin(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}