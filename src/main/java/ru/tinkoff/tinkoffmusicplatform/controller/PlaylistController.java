package ru.tinkoff.tinkoffmusicplatform.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.tinkoffmusicplatform.dto.request.InteractWithPlaylistDTO;
import ru.tinkoff.tinkoffmusicplatform.dto.request.PlaylistBodyDTO;
import ru.tinkoff.tinkoffmusicplatform.dto.response.PlaylistsDTO;
import ru.tinkoff.tinkoffmusicplatform.dto.response.PlaylistsSongsDTO;
import ru.tinkoff.tinkoffmusicplatform.dto.response.ResponseMessageDTO;
import ru.tinkoff.tinkoffmusicplatform.service.PlaylistService;

import java.util.List;

import static ru.tinkoff.tinkoffmusicplatform.constants.ErrorMessageKeeper.PLAYLIST_NOT_CREATED;
import static ru.tinkoff.tinkoffmusicplatform.constants.ResultMessageKeeper.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping
    public ResponseEntity<PlaylistsDTO> getAllPlaylists() {
        PlaylistsDTO responseDTO = new PlaylistsDTO();
        responseDTO.setPlaylists(playlistService.getAllPlaylists());
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{playlistId}")
    public ResponseEntity<List<PlaylistsSongsDTO>> getPlaylistsSongs(@PathVariable Long playlistId) {

        return ResponseEntity.ok(playlistService.getPlaylistsSongs(playlistId));
    }

    @PostMapping
    public ResponseEntity<ResponseMessageDTO> createPlaylist(@RequestBody PlaylistBodyDTO requestDTO) {
        ResponseMessageDTO responseDTO = new ResponseMessageDTO();

        try {
            playlistService.savePlaylist(requestDTO.id(), requestDTO.title(), requestDTO.description());
            responseDTO.setMessage(PLAYLIST_WAS_CREATED);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {

            responseDTO.setMessage(PLAYLIST_NOT_CREATED);
            return ResponseEntity
                    .badRequest()
                    .body(responseDTO);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseMessageDTO> addSongToPlaylist(@RequestBody InteractWithPlaylistDTO requestDTO) {
        ResponseMessageDTO responseDTO = new ResponseMessageDTO();
        playlistService.addPlaylistsSong(requestDTO.getSongId(), requestDTO.getPlaylistId());
        responseDTO.setMessage(SONG_WAS_ADDED_TO_PLAYLIST);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseMessageDTO> deleteSongFromPlaylist(@RequestBody InteractWithPlaylistDTO requestDTO) {
        ResponseMessageDTO responseDTO = new ResponseMessageDTO();
        playlistService.deletePlaylistSong(requestDTO.getSongId(), requestDTO.getPlaylistId());
        responseDTO.setMessage(SONG_WAS_DELETED_FROM_PLAYLIST);

        return ResponseEntity.ok(responseDTO);
    }

}
