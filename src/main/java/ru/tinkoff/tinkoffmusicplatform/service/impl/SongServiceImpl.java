package ru.tinkoff.tinkoffmusicplatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.repository.SongRepository;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    @Override
    public ResponseEntity<Iterable<Song>> getAllSongs() {
        return ResponseEntity.ok(songRepository.findAll());
    }

    @Override
    public ResponseEntity<Song> getSongById(Integer id) {
        if (songRepository.findById(id).isPresent()) {
            return ResponseEntity.ok(songRepository.findById(id).get());
        } else {
            return ResponseEntity.badRequest().body(Song.builder().title("null").build());
        }
    }
}
