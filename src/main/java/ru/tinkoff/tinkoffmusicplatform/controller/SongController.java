package ru.tinkoff.tinkoffmusicplatform.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.dto.request.SongBodyDTO;
import ru.tinkoff.tinkoffmusicplatform.dto.response.ResponseMessageDTO;
import ru.tinkoff.tinkoffmusicplatform.dto.response.SongListDTO;
import ru.tinkoff.tinkoffmusicplatform.mapper.SongsListMapper;
import ru.tinkoff.tinkoffmusicplatform.service.MinioService;
import ru.tinkoff.tinkoffmusicplatform.service.SongService;

import java.util.List;

import static ru.tinkoff.tinkoffmusicplatform.constants.ErrorMessageKeeper.SONG_NOT_ADDED;
import static ru.tinkoff.tinkoffmusicplatform.constants.ErrorMessageKeeper.SONG_NOT_DELETED;
import static ru.tinkoff.tinkoffmusicplatform.constants.ResultMessageKeeper.SONG_WAS_CREATED;
import static ru.tinkoff.tinkoffmusicplatform.constants.ResultMessageKeeper.SONG_WAS_DELETED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/songs")
public class SongController {

    private final SongService songService;
    private final MinioService minioService;

    @GetMapping
    public ResponseEntity<List<SongListDTO>> getAllSongs() {
        List<Song> songs = songService.getAllSongs();
        List<SongListDTO> dtoList = SongsListMapper.mapSongsListToDTO(songs);
        setSongsPictures(dtoList);

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        return ResponseEntity.ok(songService.getSongById(id));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<SongListDTO>> getSongsByTitle(@PathVariable("title") String title) {
        List<Song> songs = songService.getSongsByTitle(title);
        List<SongListDTO> dtoList = SongsListMapper.mapSongsListToDTO(songs);
        setSongsPictures(dtoList);

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<SongListDTO>> getSongsByAuthor(@PathVariable("author") String author) {
        List<Song> songs = songService.getSongsByAuthor(author);
        List<SongListDTO> dtoList = SongsListMapper.mapSongsListToDTO(songs);
        setSongsPictures(dtoList);

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<SongListDTO>> getSongsByGenre(@PathVariable("genre") String genre) {
        List<Song> songs = songService.getSongsByGenre(genre);
        List<SongListDTO> dtoList = SongsListMapper.mapSongsListToDTO(songs);
        setSongsPictures(dtoList);

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<SongListDTO>> getSortedSongs() {
        List<Song> songs = songService.getSongsSortedByGenre();
        List<SongListDTO> dtoList = SongsListMapper.mapSongsListToDTO(songs);
        setSongsPictures(dtoList);

        return ResponseEntity.ok(dtoList);
    }

    @PostMapping
    public ResponseEntity<ResponseMessageDTO> saveSong(@RequestBody SongBodyDTO requestDTO) {
        ResponseMessageDTO responseDTO = new ResponseMessageDTO();

        try {
            songService.save(requestDTO.id(),
                    requestDTO.title(),
                    requestDTO.author(),
                    requestDTO.genre(),
                    requestDTO.fileName(),
                    requestDTO.pictureName());
            responseDTO.setMessage(SONG_WAS_CREATED);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {

            responseDTO.setMessage(SONG_NOT_ADDED);
            return ResponseEntity
                    .badRequest()
                    .body(responseDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessageDTO> deleteSong(@PathVariable Long id) {
        ResponseMessageDTO responseDTO = new ResponseMessageDTO();

        try {
            songService.deleteById(id);
            responseDTO.setMessage(SONG_WAS_DELETED);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {

            responseDTO.setMessage(SONG_NOT_DELETED);
            return ResponseEntity
                    .badRequest()
                    .body(responseDTO);
        }
    }

    private void setSongsPictures(List<SongListDTO> dtoList) {
        for (SongListDTO dto : dtoList) {
            try {
                String picturePath = minioService.getSongsPicturePath(dto.getId());
                dto.setPicturePath(picturePath);
            } catch (Exception ignored) {
            }
        }
    }

}
