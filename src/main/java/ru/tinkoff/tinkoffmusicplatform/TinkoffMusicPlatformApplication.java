package ru.tinkoff.tinkoffmusicplatform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.repository.SongRepository;

@SpringBootApplication
public class TinkoffMusicPlatformApplication implements CommandLineRunner {

    @Autowired
    private SongRepository songRepository;

    public static void main(String[] args) {
        SpringApplication.run(TinkoffMusicPlatformApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.songRepository.save(Song.builder().title("Гори ясно")
                .author("PUSSYKILLER").genre("Хип-Хоп").build());
        this.songRepository.save(Song.builder().title("Blues")
                .author("Markul").genre("Хип-Хоп").build());
        this.songRepository.save(Song.builder().title("Moulin rouge")
                .author("PUSSYKILLER").genre("Хип-Хоп").build());

    }
}
