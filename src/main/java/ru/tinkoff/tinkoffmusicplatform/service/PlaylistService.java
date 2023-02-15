package ru.tinkoff.tinkoffmusicplatform.service;

import ru.tinkoff.tinkoffmusicplatform.data.Playlist;

import java.util.List;

public interface PlaylistService {

    List<Playlist> getAllPlaylists();

    void savePlaylist(Long id,
                      String title,
                      String description);

    void addPlaylistsSong(Long songId,
                          Long playlistId);

    void deletePlaylistSong(Long songId,
                            Long playlistId);
}
