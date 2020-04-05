package com.mp.notes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NotesApplication {
// https://www.callicoder.com/spring-boot-log4j-2-example/
	private static final Logger logger = LogManager.getLogger(NotesApplication.class);
	
	public static void main(String[] args) {
		logger.info("Ha bhai main spring boot hu!");
		SpringApplication.run(NotesApplication.class, args);
	}

}
