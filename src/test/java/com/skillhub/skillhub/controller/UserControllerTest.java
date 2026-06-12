package com.skillhub.skillhub.controller;

import com.skillhub.skillhub.entity.User;
import com.skillhub.skillhub.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        when(userService.findAll()).thenReturn(List.of(new User()));
        assertEquals(1, userController.getAll().size());
    }

    @Test
    void testGetById() {
        User user = new User();
        when(userService.findById(1L)).thenReturn(user);
        assertEquals(user, userController.getById(1L));
    }

    @Test
    void testCreateStudent() {
        User user = new User();
        when(userService.createStudent(any())).thenReturn(user);
        assertEquals(user, userController.createStudent(user));
    }

    @Test
    void testCreateTeacher() {
        User user = new User();
        when(userService.createTeacher(any())).thenReturn(user);
        assertEquals(user, userController.createTeacher(user));
    }

    @Test
    void testCreateAdmin() {
        User user = new User();
        when(userService.createAdmin(any())).thenReturn(user);
        assertEquals(user, userController.createAdmin(user));
    }

    @Test
    void testUpdate() {
        User user = new User();
        when(userService.update(eq(1L), any())).thenReturn(user);
        assertEquals(user, userController.update(1L, user));
    }

    @Test
    void testDelete() {
        userController.delete(1L);
        verify(userService).delete(1L);
    }
}