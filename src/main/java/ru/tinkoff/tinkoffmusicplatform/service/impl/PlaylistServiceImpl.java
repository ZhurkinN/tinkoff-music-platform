package ru.tinkoff.tinkoffmusicplatform.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.tinkoffmusicplatform.data.Playlist;
import ru.tinkoff.tinkoffmusicplatform.repository.PlaylistRepository;
import ru.tinkoff.tinkoffmusicplatform.service.PlaylistService;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;

    @Override
    public void savePlaylist(Long id, String title, String description) {

        Playlist playlist = new Playlist()
                .setId(id)
                .setTitle(title)
                .setDescription(description);
        playlistRepository.save(playlist);
    }

    @Override
    public void addSong(Long songId, Long playlistId) {


    }
}
