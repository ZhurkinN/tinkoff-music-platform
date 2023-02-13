package ru.tinkoff.tinkoffmusicplatform.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.tinkoffmusicplatform.dto.request.AddSongToPlaylistDTO;
import ru.tinkoff.tinkoffmusicplatform.dto.request.PlaylistBodyDTO;
import ru.tinkoff.tinkoffmusicplatform.dto.response.ResponseMessageDTO;
import ru.tinkoff.tinkoffmusicplatform.exception.SongNotFoundException;
import ru.tinkoff.tinkoffmusicplatform.service.PlaylistService;

import static ru.tinkoff.tinkoffmusicplatform.constants.ErrorMessageKeeper.PLAYLIST_NOT_CREATED;
import static ru.tinkoff.tinkoffmusicplatform.constants.ResultMessageKeeper.PLAYLIST_WAS_CREATED;
import static ru.tinkoff.tinkoffmusicplatform.constants.ResultMessageKeeper.SONG_WAS_ADDED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PlaylistController {

    private final PlaylistService playlistService;

    @PostMapping("/playlists")
    public ResponseEntity<ResponseMessageDTO> createPlaylist(@RequestBody PlaylistBodyDTO requestDTO) {
        ResponseMessageDTO responseDTO = new ResponseMessageDTO();

        try {
            playlistService.savePlaylist(requestDTO.id(), requestDTO.title(), requestDTO.description());
            responseDTO.setMessage(PLAYLIST_WAS_CREATED);
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {

            responseDTO.setMessage(PLAYLIST_NOT_CREATED);
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PostMapping("/playlists/add")
    public ResponseEntity<ResponseMessageDTO> addSongToPlaylist(@RequestBody AddSongToPlaylistDTO requestDTO) {
        ResponseMessageDTO responseDTO = new ResponseMessageDTO();

        try {
            playlistService.addPlaylistsSong(requestDTO.getSongId(), requestDTO.getPlaylistId());
            responseDTO.setMessage(SONG_WAS_ADDED);
            return ResponseEntity.ok(responseDTO);
        } catch (SongNotFoundException e) {

            responseDTO.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

}
