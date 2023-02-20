package ru.tinkoff.tinkoffmusicplatform.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.exception.SongNotFoundException;
import ru.tinkoff.tinkoffmusicplatform.repository.SongRepository;
import ru.tinkoff.tinkoffmusicplatform.service.SongService;

import java.util.List;
import java.util.Optional;

import static ru.tinkoff.tinkoffmusicplatform.constants.ErrorMessageKeeper.*;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @Override
    public Song getSongById(Long id) {

        Optional<Song> song = songRepository.findById(id);
        if (song.isPresent()) {
            return song.get();
        } else {
            throw new SongNotFoundException(SONG_WITH_THIS_ID_NOT_FOUND);
        }
    }

    @Override
    public List<Song> getSongsByTitle(String title) {
        if (!songRepository.findByTitle(title).isEmpty()) {
            return songRepository.findByTitle(title);
        } else {
            throw new SongNotFoundException(SONG_WITH_THIS_TITLE_NOT_FOUND);
        }
    }

    @Override
    public List<Song> getSongsByAuthor(String author) {
        if (!songRepository.findByAuthor(author).isEmpty()) {
            return songRepository.findByAuthor(author);
        } else {
            throw new SongNotFoundException(SONG_WITH_THIS_AUTHOR_NOT_FOUND);
        }
    }

    @Override
    public List<Song> getSongsByGenre(String genre) {
        if (!songRepository.findByGenre(genre).isEmpty()) {
            return songRepository.findByGenre(genre);
        } else {
            throw new SongNotFoundException(SONG_WITH_THIS_GENRE_NOT_FOUND);
        }
    }

    @Override
    public List<Song> getSongsSortedByGenre() {
        return songRepository.findAllByOrderByGenre();
    }

    @Override
    public void save(Long id,
                     String title,
                     String author,
                     String genre,
                     String fileName,
                     String pictureName) {
        Song song = new Song()
                .setId(id)
                .setTitle(title)
                .setAuthor(author)
                .setGenre(genre)
                .setFileName(fileName)
                .setPictureName(pictureName);

        songRepository.save(song);
    }

    @Override
    public void deleteById(Long id) {

        Optional<Song> song = songRepository.findById(id);
        if (song.isPresent()) {
            songRepository.delete(song.get());
        } else {
            throw new SongNotFoundException(SONG_NOT_DELETED);
        }

    }
}

