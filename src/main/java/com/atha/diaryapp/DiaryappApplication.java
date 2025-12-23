package com.atha.diaryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class DiaryappApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiaryappApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner dataLoader(@Autowired DiaryRepository diaryRepository) {
		return args -> {
			if (diaryRepository.count() == 0) {
				diaryRepository.save(new Diary("Diary pertama", "Isi diary pertama"));
				diaryRepository.save(new Diary("Diary kedua", "Isi diary kedua"));
				diaryRepository.save(new Diary("Diary ketiga", "Isi diary ketiga"));
			}
		};
	}

}
