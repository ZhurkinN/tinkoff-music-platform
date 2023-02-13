package ru.tinkoff.tinkoffmusicplatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.exception.SongNotFoundException;
import ru.tinkoff.tinkoffmusicplatform.repository.SongRepository;

import java.util.List;
import java.util.Optional;

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
        return this.songRepository.findById(id).orElseThrow(
                () -> new SongNotFoundException("Song with id " + id + " not found")
        );
    }

    @Override
    public Song createSong(Song song) {
        return this.songRepository.save(song);
    }

    @Override
    public Song updateSong(Long id, Song song) {
        Song currentSong = this.songRepository.findById(id).orElseThrow(RuntimeException::new);
        currentSong.setTitle(song.getTitle());
        currentSong.setAuthor(song.getAuthor());
        currentSong.setGenre(song.getGenre());
        return currentSong;
    }

    @Override
    public Song deleteSong(Long id) {
        Optional<Song> deletedSong = this.songRepository.findById(id);
        deletedSong.ifPresent(this.songRepository::delete);
        return deletedSong.get();
    }
}
