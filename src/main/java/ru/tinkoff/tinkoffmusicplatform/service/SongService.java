package ru.tinkoff.tinkoffmusicplatform.service;

import org.springframework.http.ResponseEntity;
import ru.tinkoff.tinkoffmusicplatform.data.Song;

public interface SongService {

    ResponseEntity<Iterable<Song>> getAllSongs();

    ResponseEntity<Song> getSongById(Integer id);
}
