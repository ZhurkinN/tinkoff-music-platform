package ru.tinkoff.tinkoffmusicplatform.service;

import ru.tinkoff.tinkoffmusicplatform.data.Playlist;
import ru.tinkoff.tinkoffmusicplatform.data.PlaylistSongs;
import ru.tinkoff.tinkoffmusicplatform.dto.response.PlaylistsSongsDTO;

import java.util.List;

public interface PlaylistService {

    List<Playlist> getAllPlaylists();

    List<PlaylistsSongsDTO> getPlaylistsSongs(Long playlistId);

    void savePlaylist(Long id,
                      String title,
                      String description);

    PlaylistSongs addPlaylistsSong(Long songId,
                                   Long playlistId);

    void deletePlaylistSong(Long songId,
                            Long playlistId);
}
