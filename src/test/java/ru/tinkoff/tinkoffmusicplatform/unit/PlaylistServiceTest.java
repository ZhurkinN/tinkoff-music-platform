package ru.tinkoff.tinkoffmusicplatform.unit;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.tinkoff.tinkoffmusicplatform.data.Playlist;
import ru.tinkoff.tinkoffmusicplatform.repository.PlaylistRepository;
import ru.tinkoff.tinkoffmusicplatform.repository.PlaylistSongsRepository;
import ru.tinkoff.tinkoffmusicplatform.repository.SongRepository;
import ru.tinkoff.tinkoffmusicplatform.service.impl.PlaylistServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static ru.tinkoff.tinkoffmusicplatform.constants.ErrorMessageKeeper.*;

@ExtendWith(MockitoExtension.class)
public class PlaylistServiceTest {

    @Mock
    private PlaylistRepository playlistRepository;
    @Mock
    private SongRepository songRepository;
    @Mock
    private PlaylistSongsRepository playlistSongsRepository;
    @InjectMocks
    private PlaylistServiceImpl playlistService;

    private Playlist playlist;

    @BeforeEach
    public void setup() {
        long id = 1L;
        String title = "Test title";
        String description = "Test description";

        playlist = new Playlist()
                .setId(id)
                .setTitle(title)
                .setDescription(description);
    }

    @Test
    public void itShouldReturnAllPlaylists() {
        when(playlistRepository.findAll()).thenReturn(List.of(playlist));

        List<Playlist> playlists = playlistService.getAllPlaylists();

        assertThat(playlists).isNotEmpty();
    }
}
