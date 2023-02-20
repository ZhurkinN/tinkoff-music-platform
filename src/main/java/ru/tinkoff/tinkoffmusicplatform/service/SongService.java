package ru.tinkoff.tinkoffmusicplatform.service;

import ru.tinkoff.tinkoffmusicplatform.data.Song;

import java.util.List;

public interface SongService {

    List<Song> getAllSongs();

    Song getSongById(Long id);

    List<Song> getSongsByTitle(String title);

    List<Song> getSongsByAuthor(String author);

    List<Song> getSongsByGenre(String genre);

    List<Song> getSongsSortedByGenre();

    void save(Long id,
              String title,
              String author,
              String genre,
              String fileName,
              String pictureName);

    void deleteById(Long id);
}
