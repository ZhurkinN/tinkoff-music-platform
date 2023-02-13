package ru.tinkoff.tinkoffmusicplatform.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.repository.SongRepository;
import ru.tinkoff.tinkoffmusicplatform.service.SongService;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    @Override
    public ResponseEntity<Iterable<Song>> getAllSongs() {
        return ResponseEntity.ok(songRepository.findAll());
    }

    @Override
    public ResponseEntity<Iterable<Song>> getAllSongsSortedByGenre() {
        return ResponseEntity.ok(songRepository.findAllByOrderByGenre());
    }

    @Override
    public ResponseEntity<Iterable<Song>> getSongsByTitle(String title) {
        if (((Collection<Song>) songRepository.findByTitle(title)).size() != 0){
            return ResponseEntity.ok(songRepository.findByTitle(title));
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Iterable<Song>> getSongsByAuthor(String author) {
         if (((Collection<Song>) songRepository.findByAuthor(author)).size() != 0){
            return ResponseEntity.ok(songRepository.findByAuthor(author));
         }
         else{
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
    }

    @Override
    public ResponseEntity<Iterable<Song>> getSongsByGenre(String genre) {
        if(((Collection<Song>) songRepository.findByGenre(genre)).size() != 0){
            return ResponseEntity.ok(songRepository.findByGenre(genre));
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Song> getSongById(Integer id) {
        if (songRepository.findById(id).isPresent()) {
            return ResponseEntity.ok(songRepository.findById(id).get());
        } else {
            return ResponseEntity.badRequest().body(Song.builder().title("null").build());
        }
    }

    @Override
    public void save(Song song) {
        songRepository.save(song);
    }
}

