package com.atha.diaryapp;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/diaries")
public class DiaryController {
    private final DiaryService diaryService;

    @Autowired
    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping
    public ResponseEntity<DiaryResponseDTO> createDiary(@Valid @RequestBody DiaryRequestDTO request) {
        Diary diary = new Diary();
        diary.setTitle(request.getTitle());
        diary.setContent(request.getContent());
        Diary created = diaryService.createDiary(diary);
        DiaryResponseDTO response = new DiaryResponseDTO(
                created.getId(), created.getTitle(), created.getContent(), created.getCreatedAt()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DiaryResponseDTO>> getAllDiaries() {
        List<DiaryResponseDTO> result = diaryService.getAllDiaries().stream()
                .map(d -> new DiaryResponseDTO(d.getId(), d.getTitle(), d.getContent(), d.getCreatedAt()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiaryResponseDTO> getDiaryById(@PathVariable Long id) {
        return diaryService.getDiaryById(id)
                .map(d -> new DiaryResponseDTO(d.getId(), d.getTitle(), d.getContent(), d.getCreatedAt()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiaryResponseDTO> updateDiary(@PathVariable Long id, @Valid @RequestBody DiaryRequestDTO request) {
        try {
            Diary diary = new Diary();
            diary.setTitle(request.getTitle());
            diary.setContent(request.getContent());
            Diary updated = diaryService.updateDiary(id, diary);
            DiaryResponseDTO response = new DiaryResponseDTO(
                    updated.getId(), updated.getTitle(), updated.getContent(), updated.getCreatedAt()
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiary(@PathVariable Long id) {
        diaryService.deleteDiary(id);
        return ResponseEntity.noContent().build();
    }
}
