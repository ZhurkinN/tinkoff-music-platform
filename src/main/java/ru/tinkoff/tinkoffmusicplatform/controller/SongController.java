package ru.tinkoff.tinkoffmusicplatform.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.dto.request.InteractWithSongDTO;
import ru.tinkoff.tinkoffmusicplatform.dto.request.SongBodyDTO;
import ru.tinkoff.tinkoffmusicplatform.dto.response.ResponseMessageDTO;
import ru.tinkoff.tinkoffmusicplatform.dto.response.SongsDTO;
import ru.tinkoff.tinkoffmusicplatform.service.SongService;

import static ru.tinkoff.tinkoffmusicplatform.constants.ErrorMessageKeeper.SONG_NOT_ADDED;
import static ru.tinkoff.tinkoffmusicplatform.constants.ErrorMessageKeeper.SONG_NOT_DELETED;
import static ru.tinkoff.tinkoffmusicplatform.constants.ResultMessageKeeper.SONG_WAS_CREATED;
import static ru.tinkoff.tinkoffmusicplatform.constants.ResultMessageKeeper.SONG_WAS_DELETED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/songs")
public class SongController {

    private final SongService songService;

    @GetMapping
    public ResponseEntity<SongsDTO> getAllSongs() {
        SongsDTO responseDTO = new SongsDTO();
        responseDTO.setSongs(songService.getAllSongs());
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        return ResponseEntity.ok(songService.getSongById(id));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<SongsDTO> getSongsByTitle(@PathVariable("title") String title) {
        SongsDTO responseDTO = new SongsDTO();
        responseDTO.setSongs(songService.getSongsByTitle(title));
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<SongsDTO> getSongsByAuthor(@PathVariable("author") String author) {
        SongsDTO responseDTO = new SongsDTO();
        responseDTO.setSongs(songService.getSongsByAuthor(author));
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<SongsDTO> getSongsByGenre(@PathVariable("genre") String genre) {
        SongsDTO responseDTO = new SongsDTO();
        responseDTO.setSongs(songService.getSongsByGenre(genre));
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/sorted")
    public ResponseEntity<SongsDTO> getSortedSongs() {
        SongsDTO responseDTO = new SongsDTO();
        responseDTO.setSongs(songService.getSongsSortedByGenre());
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseMessageDTO> saveSong(@RequestBody SongBodyDTO requestDTO) {
        ResponseMessageDTO responseDTO = new ResponseMessageDTO();

        try {
            songService.save(requestDTO.id(),
                    requestDTO.title(),
                    requestDTO.author(),
                    requestDTO.genre());
            responseDTO.setMessage(SONG_WAS_CREATED);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {

            responseDTO.setMessage(SONG_NOT_ADDED);
            return ResponseEntity
                    .badRequest()
                    .body(responseDTO);
        }
    }

    @DeleteMapping
    public ResponseEntity<ResponseMessageDTO> deleteSong(@RequestBody InteractWithSongDTO requestDTO) {
        ResponseMessageDTO responseDTO = new ResponseMessageDTO();

        try {
            songService.deleteById(requestDTO.getSongId());
            responseDTO.setMessage(SONG_WAS_DELETED);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {

            responseDTO.setMessage(SONG_NOT_DELETED);
            return ResponseEntity
                    .badRequest()
                    .body(responseDTO);
        }
    }

}