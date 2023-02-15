package ru.tinkoff.tinkoffmusicplatform.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.exception.SongNotFoundException;
import ru.tinkoff.tinkoffmusicplatform.repository.SongRepository;
import ru.tinkoff.tinkoffmusicplatform.service.SongService;
import static ru.tinkoff.tinkoffmusicplatform.constants.ErrorMessageKeeper.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    @Override
    public ResponseEntity<List<Song>> getAllSongs() {
        return ResponseEntity.ok(songRepository.findAll());
    }

    @Override
    public ResponseEntity<List<Song>> getAllSongsSortedByGenre() {
        return ResponseEntity.ok(songRepository.findAllByOrderByGenre());
    }

    @Override
    public ResponseEntity<List<Song>> getSongsByTitle(String title) {
        if ((songRepository.findByTitle(title)).size() != 0) {
            return ResponseEntity.ok(songRepository.findByTitle(title));
        } else {
            throw new SongNotFoundException(SONG_WITH_THIS_TITLE_NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<Song>> getSongsByAuthor(String author) {
        if ((songRepository.findByAuthor(author)).size() != 0) {
            return ResponseEntity.ok(songRepository.findByAuthor(author));
        } else {
            throw new SongNotFoundException(SONG_WITH_THIS_AUTHOR_NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<Song>> getSongsByGenre(String genre) {
        if ((songRepository.findByGenre(genre)).size() != 0) {
            return ResponseEntity.ok(songRepository.findByGenre(genre));
        } else {
            throw new SongNotFoundException(SONG_WITH_THIS_GENRE_NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Song> getSongById(Long id) {
        if (songRepository.findById(id).isPresent()) {
            return ResponseEntity.ok(songRepository.findById(id).get());
        } else {
            throw new SongNotFoundException(SONG_WITH_THIS_ID_NOT_FOUND);
        }
    }

    @Override
    public void save(Song song) {
        songRepository.save(song);
    }

    @Override
    public void deleteById(Long id) {

        Optional<Song> song = songRepository.findById(id);

        if (song.isPresent()) {
            songRepository.delete(song.get());
        } else {
            throw new SongNotFoundException(SONG_CANNOT_BE_DELETED);
        }

    }
}

