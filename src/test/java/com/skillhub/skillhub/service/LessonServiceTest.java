package com.skillhub.skillhub.service;

import com.skillhub.skillhub.entity.Lesson;
import com.skillhub.skillhub.repository.LessonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LessonServiceTest {

    @Mock
    private LessonRepository lessonRepository;

    @InjectMocks
    private LessonService lessonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        when(lessonRepository.findAll()).thenReturn(List.of(new Lesson()));
        assertEquals(1, lessonService.findAll().size());
    }

    @Test
    void testFindById() {
        Lesson lesson = new Lesson();
        when(lessonRepository.findById(1L)).thenReturn(Optional.of(lesson));
        assertEquals(lesson, lessonService.findById(1L));
    }

    @Test
    void testFindByIdNotFound() {
        when(lessonRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> lessonService.findById(1L));
    }

    @Test
    void testSave() {
        Lesson lesson = new Lesson();
        when(lessonRepository.save(any())).thenReturn(lesson);
        assertEquals(lesson, lessonService.save(lesson));
    }

    @Test
    void testUpdate() {
        Lesson existing = new Lesson();
        existing.setTitle("Old");

        Lesson updated = new Lesson();
        updated.setTitle("New");
        updated.setContent("NewContent");

        when(lessonRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(lessonRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Lesson result = lessonService.update(1L, updated);
        assertEquals("New", result.getTitle());
        assertEquals("NewContent", result.getContent());
    }

    @Test
    void testDelete() {
        lessonService.delete(1L);
        verify(lessonRepository).deleteById(1L);
    }
}