package com.atha.diaryapp;

import java.util.List;
import java.util.Optional;

public interface DiaryService {
    Diary createDiary(Diary diary);
    List<Diary> getAllDiaries();
    Optional<Diary> getDiaryById(Long id);
    Diary updateDiary(Long id, Diary diary);
    void deleteDiary(Long id);
}
