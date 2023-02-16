package ru.tinkoff.tinkoffmusicplatform.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.dto.response.ResponseMessageDTO;
import ru.tinkoff.tinkoffmusicplatform.dto.songDto.DeleteSongDTO;
import ru.tinkoff.tinkoffmusicplatform.service.SongService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/songs")
public class SongController {

    private final SongService songService;

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongFindById(@PathVariable Long id) {
        return this.songService.getSongById(id);
    }

    @GetMapping
    public ResponseEntity<List<Song>> getSongs() {
        return this.songService.getAllSongs();
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Song>> getSongsSortedByGenre(){
        return songService.getAllSongsSortedByGenre();
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Song>> getSongsByTitle(@PathVariable("title") String title){
        return songService.getSongsByTitle(title);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Song>> getSongsByAuthor(@PathVariable("author") String author){
        return songService.getSongsByAuthor(author);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Song>> getSongsByGenre(@PathVariable("genre") String genre){
        return songService.getSongsByGenre(genre);
    }

    @PostMapping
    public ResponseEntity<ResponseMessageDTO> save(@RequestBody Song song){
        ResponseMessageDTO responseMessageDTO = new ResponseMessageDTO();

        try{
            songService.save(song);

            responseMessageDTO.setMessage("Song was added");

            return ResponseEntity.ok(responseMessageDTO);
        }
        catch (Exception e){
            responseMessageDTO.setMessage("Song wasn't added");

            return ResponseEntity.badRequest().body(responseMessageDTO);
        }
    }

    @DeleteMapping
    public ResponseEntity<ResponseMessageDTO> delete(@RequestBody DeleteSongDTO song){
        ResponseMessageDTO responseMessageDTO = new ResponseMessageDTO();

        try{

            songService.deleteById(song.getSongId());

            responseMessageDTO.setMessage("Song was deleted");

            return ResponseEntity.ok(responseMessageDTO);
        }
        catch (Exception e){
            responseMessageDTO.setMessage("Song wasn't deleted");

            return ResponseEntity.badRequest().body(responseMessageDTO);
        }
    }

}