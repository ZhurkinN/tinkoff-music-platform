package ru.tinkoff.tinkoffmusicplatform.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.service.SongService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/songs")
@CrossOrigin("http://localhost:3000/")
public class SongController {

    private final SongService songService;

    @GetMapping("/{id}")
    public Song getSongFindById(@PathVariable Long id) {
        return this.songService.getSongById(id);
    }

    @GetMapping
    public Iterable<Song> getSongs() {
        return this.songService.getAllSongs();
    }
}
