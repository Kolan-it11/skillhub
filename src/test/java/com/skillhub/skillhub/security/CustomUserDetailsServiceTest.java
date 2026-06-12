package com.skillhub.skillhub.security;

import com.skillhub.skillhub.entity.Role;
import com.skillhub.skillhub.entity.User;
import com.skillhub.skillhub.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService service;

    public CustomUserDetailsServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUser() {

        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("pass");

        Role role = new Role();
        role.setName("TEACHER");
        user.setRoles(Set.of(role));

        when(userRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.of(user));

        UserDetails result = service.loadUserByUsername("test@test.com");

        assertEquals("test@test.com", result.getUsername());
    }
}