package ru.tinkoff.tinkoffmusicplatform.service;

import ru.tinkoff.tinkoffmusicplatform.data.Song;

public interface SongService {

    Iterable<Song> getAllSongs();

    Song getSongById(Long id);
}
