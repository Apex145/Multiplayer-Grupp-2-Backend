package com.multiplayer.pokedodge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // without this the @Scheduled game loop never runs, with no error
public class PokedodgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedodgeApplication.class, args);
	}

}
