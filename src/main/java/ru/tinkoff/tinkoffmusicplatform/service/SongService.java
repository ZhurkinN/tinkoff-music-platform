package ru.tinkoff.tinkoffmusicplatform.service;

import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.exception.SongNotFoundException;

import java.net.URISyntaxException;
import java.util.List;

public interface SongService {

    List<Song> getAllSongs();

    Song getSongById(Long id) throws SongNotFoundException;

    Song createSong(Song song) throws URISyntaxException;

    Song updateSong(Long id, Song song);

    Song deleteSong(Long id);
}
