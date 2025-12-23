package com.atha.diaryapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DiaryController.class)
class DiaryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DiaryService diaryService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateDiary() throws Exception {
        Diary diary = new Diary();
        diary.setId(1L);
        diary.setTitle("Judul");
        diary.setContent("Isi");
        when(diaryService.createDiary(any(Diary.class))).thenReturn(diary);
        DiaryRequestDTO req = new DiaryRequestDTO();
        req.setTitle("Judul");
        req.setContent("Isi");
        mockMvc.perform(post("/api/diaries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Judul"));
    }

    @Test
    void testGetAllDiaries() throws Exception {
        Diary d1 = new Diary(); d1.setId(1L); d1.setTitle("A");
        Diary d2 = new Diary(); d2.setId(2L); d2.setTitle("B");
        when(diaryService.getAllDiaries()).thenReturn(Arrays.asList(d1, d2));
        mockMvc.perform(get("/api/diaries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("A"));
    }

    @Test
    void testGetDiaryById() throws Exception {
        Diary diary = new Diary(); diary.setId(1L); diary.setTitle("X");
        when(diaryService.getDiaryById(1L)).thenReturn(Optional.of(diary));
        mockMvc.perform(get("/api/diaries/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("X"));
    }

    @Test
    void testUpdateDiary() throws Exception {
        Diary diary = new Diary(); diary.setId(1L); diary.setTitle("Baru");
        when(diaryService.updateDiary(Mockito.eq(1L), any(Diary.class))).thenReturn(diary);
        DiaryRequestDTO req = new DiaryRequestDTO();
        req.setTitle("Baru");
        req.setContent("Isi baru");
        mockMvc.perform(put("/api/diaries/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Baru"));
    }

    @Test
    void testDeleteDiary() throws Exception {
        mockMvc.perform(delete("/api/diaries/1"))
                .andExpect(status().isNoContent());
    }
}
