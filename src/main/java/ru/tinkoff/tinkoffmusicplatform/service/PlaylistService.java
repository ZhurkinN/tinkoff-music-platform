package ru.tinkoff.tinkoffmusicplatform.service;

public interface PlaylistService {

    void savePlaylist(Long id, String title, String description);

    void addSong(Long songId, Long playlistId);
}
