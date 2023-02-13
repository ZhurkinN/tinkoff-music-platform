package ru.tinkoff.tinkoffmusicplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TinkoffMusicPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinkoffMusicPlatformApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner run(SongRepository songRepository) {
//		return args -> {
//			songRepository.deleteAll();
//			songRepository.save(Song.builder()
//					.title("tes1").build());
//			songRepository.save(Song.builder().
//					title("test2").build());
//			songRepository.save(Song.builder().
//					title("test3").build());
//		};
//	}
}
