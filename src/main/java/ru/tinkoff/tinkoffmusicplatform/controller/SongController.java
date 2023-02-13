package ru.tinkoff.tinkoffmusicplatform.controller;

import org.springframework.web.bind.annotation.*;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.service.SongService;

import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/{id}")
    public Song getSongFindById(@PathVariable Long id) {
        return this.songService.getSongById(id);
    }

    @GetMapping
    public List<Song> getSongs() {
        return this.songService.getAllSongs();
    }

    @PostMapping
    public Song createSong(@RequestBody Song song) throws URISyntaxException {
        return this.songService.createSong(song);
    }

    @PutMapping("/{id}")
    public Song updateSong(@PathVariable Long id, @RequestBody Song song) {
        return this.songService.updateSong(id, song);
    }

    @DeleteMapping("{/id}")
    public Song deleteSong(@PathVariable Long id) {
        return this.songService.deleteSong(id);
    }

}
