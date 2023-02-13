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
import ru.tinkoff.tinkoffmusicplatform.service.PlaylistService;

import static ru.tinkoff.tinkoffmusicplatform.constants.ErrorMessageKeeper.PLAYLIST_WASNT_CREATED;
import static ru.tinkoff.tinkoffmusicplatform.constants.ResultMessageKeeper.PLAYLIST_WAS_CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PlaylistController {

    private final PlaylistService playlistService;

    @PostMapping("/playlists")
    public ResponseEntity<ResponseMessageDTO> createPlaylist(@RequestBody PlaylistBodyDTO playlistBodyDTO) {
        ResponseMessageDTO messageDTO = new ResponseMessageDTO();

        try {
            playlistService.savePlaylist(playlistBodyDTO.id(), playlistBodyDTO.title(), playlistBodyDTO.description());
            messageDTO.setMessage(PLAYLIST_WAS_CREATED);
            return ResponseEntity.ok(messageDTO);
        } catch (Exception e) {

            messageDTO.setMessage(PLAYLIST_WASNT_CREATED);
            return ResponseEntity.badRequest().body(messageDTO);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseMessageDTO> addSongToPlaylist(@RequestBody AddSongToPlaylistDTO dto) {

        return ResponseEntity.ok(new ResponseMessageDTO("OK"));
    }

}
