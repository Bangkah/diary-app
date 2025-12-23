package com.atha.diaryapp;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DiaryServiceImplTest {
    @Mock
    private DiaryRepository diaryRepository;

    @InjectMocks
    private DiaryServiceImpl diaryService;

    public DiaryServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateDiary() {
        Diary diary = new Diary();
        diary.setTitle("Test");
        diary.setContent("Isi");
        when(diaryRepository.save(any(Diary.class))).thenReturn(diary);
        Diary result = diaryService.createDiary(diary);
        assertEquals("Test", result.getTitle());
    }

    @Test
    void testGetAllDiaries() {
        Diary d1 = new Diary(); d1.setTitle("A");
        Diary d2 = new Diary(); d2.setTitle("B");
        when(diaryRepository.findAll()).thenReturn(Arrays.asList(d1, d2));
        List<Diary> result = diaryService.getAllDiaries();
        assertEquals(2, result.size());
    }

    @Test
    void testGetDiaryById() {
        Diary diary = new Diary(); diary.setTitle("X");
        when(diaryRepository.findById(1L)).thenReturn(Optional.of(diary));
        Optional<Diary> result = diaryService.getDiaryById(1L);
        assertTrue(result.isPresent());
        assertEquals("X", result.get().getTitle());
    }

    @Test
    void testUpdateDiary() {
        Diary oldDiary = new Diary(); oldDiary.setTitle("Old");
        Diary newDiary = new Diary(); newDiary.setTitle("New");
        when(diaryRepository.findById(1L)).thenReturn(Optional.of(oldDiary));
        when(diaryRepository.save(any(Diary.class))).thenReturn(newDiary);
        Diary result = diaryService.updateDiary(1L, newDiary);
        assertEquals("New", result.getTitle());
    }

    @Test
    void testDeleteDiary() {
        doNothing().when(diaryRepository).deleteById(1L);
        diaryService.deleteDiary(1L);
        verify(diaryRepository, times(1)).deleteById(1L);
    }
}
