package ru.tinkoff.tinkoffmusicplatform.service;

import org.springframework.http.ResponseEntity;
import ru.tinkoff.tinkoffmusicplatform.data.Song;

import java.util.List;

public interface SongService {

    ResponseEntity<List<Song>> getAllSongs();

    ResponseEntity<List<Song>> getAllSongsSortedByGenre();

    ResponseEntity<List<Song>> getSongsByTitle(String title);

    ResponseEntity<List<Song>> getSongsByAuthor(String author);

    ResponseEntity<List<Song>> getSongsByGenre(String genre);

    ResponseEntity<Song> getSongById(Long id);

    void save(Song song);

    void deleteById(Long id);
}
