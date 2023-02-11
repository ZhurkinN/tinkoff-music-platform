package ru.tinkoff.tinkoffmusicplatform.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.service.SongService;

@RestController
@RequestMapping("/api/v1/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongFindById(@PathVariable Integer id) {
        return this.songService.getSongById(id);
    }

    @GetMapping
    public ResponseEntity<Iterable<Song>> getSongs() {
        return this.songService.getAllSongs();
    }
}
