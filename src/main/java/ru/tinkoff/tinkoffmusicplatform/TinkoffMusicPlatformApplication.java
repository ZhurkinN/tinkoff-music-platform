package ru.tinkoff.tinkoffmusicplatform;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.repository.SongRepository;

@SpringBootApplication
public class TinkoffMusicPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinkoffMusicPlatformApplication.class, args);
	}
}
