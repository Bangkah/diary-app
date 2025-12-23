package com.atha.diaryapp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DiaryRequestDTO {
    @NotBlank(message = "Title wajib diisi")
    @Size(max = 255, message = "Title maksimal 255 karakter")
    private String title;

    @NotBlank(message = "Content wajib diisi")
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
