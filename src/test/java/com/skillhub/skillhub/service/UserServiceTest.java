package com.skillhub.skillhub.service;

import com.skillhub.skillhub.entity.Role;
import com.skillhub.skillhub.entity.User;
import com.skillhub.skillhub.repository.RoleRepository;
import com.skillhub.skillhub.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        when(userRepository.findAll()).thenReturn(List.of(new User()));
        assertEquals(1, userService.findAll().size());
    }

    @Test
    void testFindById() {
        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        assertEquals(user, userService.findById(1L));
    }

    @Test
    void testFindByIdNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.findById(1L));
    }

    @Test
    void testCreateStudent() {
        User user = new User();
        user.setPassword("123");
        Role role = new Role();
        role.setName("STUDENT");

        when(roleRepository.findByName("STUDENT")).thenReturn(Optional.of(role));
        when(passwordEncoder.encode("123")).thenReturn("encoded");
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        User result = userService.createStudent(user);
        assertEquals("encoded", result.getPassword());
        assertTrue(result.getRoles().contains(role));
    }

    @Test
    void testCreateStudentRoleNotFound() {
        when(roleRepository.findByName("STUDENT")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.createStudent(new User()));
    }

    @Test
    void testCreateTeacher() {
        User user = new User();
        user.setPassword("123");
        Role role = new Role();
        role.setName("TEACHER");

        when(roleRepository.findByName("TEACHER")).thenReturn(Optional.of(role));
        when(passwordEncoder.encode("123")).thenReturn("encoded");
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        User result = userService.createTeacher(user);
        assertTrue(result.getRoles().contains(role));
    }

    @Test
    void testCreateTeacherRoleNotFound() {
        when(roleRepository.findByName("TEACHER")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.createTeacher(new User()));
    }

    @Test
    void testUpdate() {
        User existing = new User();
        existing.setName("Old");
        existing.setEmail("old@test.com");
        existing.setPassword("oldpass");

        User updated = new User();
        updated.setName("New");
        updated.setEmail("new@test.com");
        updated.setPassword("newpass");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(passwordEncoder.encode("newpass")).thenReturn("encodednew");
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        User result = userService.update(1L, updated);
        assertEquals("New", result.getName());
        assertEquals("new@test.com", result.getEmail());
        assertEquals("encodednew", result.getPassword());
    }

    @Test
    void testDelete() {
        userService.delete(1L);
        verify(userRepository).deleteById(1L);
    }
}