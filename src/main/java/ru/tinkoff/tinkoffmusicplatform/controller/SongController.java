package ru.tinkoff.tinkoffmusicplatform.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.repository.SongStorage;

@RestController
@RequestMapping("/api/v1/songs")
public class SongController {

    private final SongStorage songStorage;

    public SongController(SongStorage songStorage) {
        this.songStorage = songStorage;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongsFindById(@PathVariable Integer id) {
        if (songStorage.findById(id).isPresent()) {
            return ResponseEntity.ok(songStorage.findById(id).get());
        } else {
            return ResponseEntity.badRequest().body(Song.builder().title("null").build());
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<Song>> getSongs() {
        return ResponseEntity.ok(songStorage.findAll());
    }
}
