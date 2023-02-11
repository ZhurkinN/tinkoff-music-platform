package ru.tinkoff.tinkoffmusicplatform;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.repository.SongStorage;

@SpringBootApplication
public class TinkoffMusicPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinkoffMusicPlatformApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(SongStorage songStorage) {
		return args -> {
			songStorage.deleteAll();
			songStorage.save(Song.builder()
					.title("tes1").build());
			songStorage.save(Song.builder().
					title("test2").build());
			songStorage.save(Song.builder().
					title("test3").build());
		};
	}
}
