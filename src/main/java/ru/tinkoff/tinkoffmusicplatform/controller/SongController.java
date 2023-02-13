package ru.tinkoff.tinkoffmusicplatform.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.service.SongService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/songs/{id}")
    public Song getSongFindById(@PathVariable Long id) {
        return this.songService.getSongById(id);
    }

    @GetMapping("/songs")
    public Iterable<Song> getSongs() {
        return this.songService.getAllSongs();
    }
}
