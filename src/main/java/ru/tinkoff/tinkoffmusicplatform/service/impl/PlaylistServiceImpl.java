package ru.tinkoff.tinkoffmusicplatform.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.tinkoffmusicplatform.data.Playlist;
import ru.tinkoff.tinkoffmusicplatform.data.PlaylistSongs;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.exception.SongNotFoundException;
import ru.tinkoff.tinkoffmusicplatform.repository.PlaylistRepository;
import ru.tinkoff.tinkoffmusicplatform.repository.PlaylistSongsRepository;
import ru.tinkoff.tinkoffmusicplatform.repository.SongRepository;
import ru.tinkoff.tinkoffmusicplatform.service.PlaylistService;

import java.util.Optional;

import static ru.tinkoff.tinkoffmusicplatform.constants.ErrorMessageKeeper.SONG_OR_PLAYLIST_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;
    private final PlaylistSongsRepository playlistSongsRepository;

    @Override
    @Transactional
    public void savePlaylist(Long id, String title, String description) {

        Playlist playlist = new Playlist()
                .setId(id)
                .setTitle(title)
                .setDescription(description);
        playlistRepository.save(playlist);
    }

    @Override
    public void addPlaylistsSong(Long songId, Long playlistId) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        Optional<Song> song = songRepository.findById(songId);

        if (playlist.isPresent() && song.isPresent()) {

            int newSongPosition = 1;
            if (!playlistSongsRepository
                    .getPlaylistSongsByPlaylist(playlist.get())
                    .isEmpty()) {
                newSongPosition = playlistSongsRepository.getLastSongPosition(playlistId) + 1;
            }

            PlaylistSongs playlistSongs = new PlaylistSongs()
                    .setSong(song.get())
                    .setPlaylist(playlist.get())
                    .setSongPosition(newSongPosition);

            playlistSongsRepository.save(playlistSongs);
        } else {
            throw new SongNotFoundException(SONG_OR_PLAYLIST_NOT_FOUND);
        }

    }
}
