package ru.tinkoff.tinkoffmusicplatform.service;

import org.springframework.http.ResponseEntity;
import ru.tinkoff.tinkoffmusicplatform.data.Song;

public interface SongService {

    ResponseEntity<Iterable<Song>> getAllSongs();

    ResponseEntity<Iterable<Song>> getAllSongsSortedByGenre();

    ResponseEntity<Iterable<Song>> getSongsByTitle(String title);

    ResponseEntity<Iterable<Song>> getSongsByAuthor(String author);

    ResponseEntity<Iterable<Song>> getSongsByGenre(String genre);

    ResponseEntity<Song> getSongById(Long id);

    void save(Song song);
}
