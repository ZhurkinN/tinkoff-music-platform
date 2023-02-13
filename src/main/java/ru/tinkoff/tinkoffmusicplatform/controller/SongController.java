package ru.tinkoff.tinkoffmusicplatform.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.service.SongService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/songs")
public class SongController {

    private final SongService songService;

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongFindById(@PathVariable Integer id) {
        return this.songService.getSongById(id);
    }

// Этим вариантом можно упаковать возврат треков без сортировки и с сортировкой
//    @GetMapping
//    public ResponseEntity<Iterable<Song>> getSongs
//            (@RequestParam(value = "sort", required = false, defaultValue = "false") boolean isSort) {
//        return isSort ? this.songService.getAllSongsSortedByGenre() : this.songService.getAllSongs();
//    }


    @GetMapping
    public ResponseEntity<Iterable<Song>> getSongs() {
        return this.songService.getAllSongs();
    }

    @GetMapping("/sorted")
    public ResponseEntity<Iterable<Song>> getSongsSortedByGenre(){
        return songService.getAllSongsSortedByGenre();
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Iterable<Song>> getSongsByTitle(@PathVariable("title") String title){
        return songService.getSongsByTitle(title);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<Iterable<Song>> getSongsByAuthor(@PathVariable("author") String author){
        return songService.getSongsByAuthor(author);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<Iterable<Song>> getSongsByGenre(@PathVariable("genre") String genre){
        return songService.getSongsByGenre(genre);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Song song){

        songService.save(song);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
