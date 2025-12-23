package com.atha.diaryapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;

    @Autowired
    public DiaryServiceImpl(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    @Override
    public Diary createDiary(Diary diary) {
        return diaryRepository.save(diary);
    }

    @Override
    public List<Diary> getAllDiaries() {
        return diaryRepository.findAll();
    }

    @Override
    public Optional<Diary> getDiaryById(Long id) {
        return diaryRepository.findById(id);
    }

    @Override
    public Diary updateDiary(Long id, Diary diary) {
        return diaryRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(diary.getTitle());
                    existing.setContent(diary.getContent());
                    return diaryRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Diary not found"));
    }

    @Override
    public void deleteDiary(Long id) {
        diaryRepository.deleteById(id);
    }
}
