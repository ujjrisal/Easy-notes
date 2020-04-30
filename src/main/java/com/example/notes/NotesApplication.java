package com.example.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
// this is a main class
public class NotesApplication {

	public static void main(String[] args) {
        SpringApplication.run(NotesApplication.class, args);
	}
}
