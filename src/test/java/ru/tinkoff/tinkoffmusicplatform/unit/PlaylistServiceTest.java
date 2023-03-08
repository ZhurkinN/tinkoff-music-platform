package ru.tinkoff.tinkoffmusicplatform.unit;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.tinkoff.tinkoffmusicplatform.data.Playlist;
import ru.tinkoff.tinkoffmusicplatform.data.PlaylistSongs;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.dto.response.PlaylistsSongsDTO;
import ru.tinkoff.tinkoffmusicplatform.exception.SongNotFoundException;
import ru.tinkoff.tinkoffmusicplatform.exception.SongNotFoundInPlaylistException;
import ru.tinkoff.tinkoffmusicplatform.repository.PlaylistRepository;
import ru.tinkoff.tinkoffmusicplatform.repository.PlaylistSongsRepository;
import ru.tinkoff.tinkoffmusicplatform.repository.SongRepository;
import ru.tinkoff.tinkoffmusicplatform.service.impl.PlaylistServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
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

    private Long playlistId = 1L;

    private Song song1;

    private Song song2;

    private PlaylistSongs playlistSongs;

    private Long playlistSongsId = 1L;

    @BeforeEach
    public void setup() {
        String title = "Test title";
        String description = "Test description";

        playlist = new Playlist()
                .setId(playlistId)
                .setTitle(title)
                .setDescription(description);

        song1 = Song.builder()
                .id(1L)
                .author("Markul")
                .title("Серпантин")
                .genre("Rap")
                .build();

        song2 = Song.builder()
                .id(2L)
                .author("ATL")
                .title("Серпантин")
                .genre("Rap")
                .build();

        playlistSongs = new PlaylistSongs(playlistSongsId, 1, song1, playlist);
    }

    /**
     * Test for getAllPlaylists() method
     */
    @Test
    public void itShouldReturnAllPlaylists() {
        when(playlistRepository.findAll()).thenReturn(List.of(playlist));

        List<Playlist> playlists = playlistService.getAllPlaylists();

        assertThat(playlists).isNotEmpty();
    }

    /**
     * Test for getPlaylistsSongs(Long songId) method
     */
    @Test
    public void itShouldReturnPlaylistOfSongs() {
        when(songRepository.findSongByPlaylistId(playlistId)).thenReturn(List.of(song1, song2));

        List<PlaylistsSongsDTO> playlistsSongsDTOS = playlistService.getPlaylistsSongs(playlistId);

        assertThat(playlistsSongsDTOS).isNotEmpty();
    }

    /**
     * Test for savePlaylist(Long id, String title, String description) method
     */
    @Test
    public void itShouldSavePlaylist() {

        when(playlistRepository.save(any(Playlist.class))).thenReturn(playlist);

        playlistService.savePlaylist(playlist.getId(), playlist.getTitle(), playlist.getDescription());

        verify(playlistRepository, times(1)).save(playlist);
    }

    /**
     * Test for addPlaylistsSong(Long songId, Long playlistId) method
     */
    // @TODO refactor this test (change checking of increment song position)
    @Test
    public void itShouldSaveSongToPlaylist() {
        when(playlistRepository.findById(playlistId)).thenReturn(Optional.of(playlist));
        when(songRepository.findById(song1.getId())).thenReturn(Optional.of(song1));
        when(songRepository.findById(song2.getId())).thenReturn(Optional.of(song2));

        when(playlistSongsRepository.save(any(PlaylistSongs.class))).thenReturn(playlistSongs);
        when(playlistSongsRepository.getPlaylistSongsByPlaylist(playlist)).thenReturn(List.of(playlistSongs));
        when(playlistSongsRepository.findBySongIdAndPlaylistId(song1.getId(), playlistId))
                .thenReturn(Optional.of(playlistSongs));
        when(playlistSongsRepository.findBySongIdAndPlaylistId(song2.getId(), playlistId))
                .thenReturn(Optional.empty());
        when(playlistSongsRepository.getLastSongPosition(playlistId)).thenReturn(1);

        int songPosition = playlistSongs.getSongPosition();

        playlistService.addPlaylistsSong(song2.getId(), playlistId);

        //assertEquals(songPosition + 1, playlistSongs.getSongPosition());
        verify(playlistSongsRepository, times(1)).save(playlistSongs);
    }

    /**
     * Test for deletePlaylistSong(Long songId, Long playlistId) method
     */
    @Test
    public void itShouldDeletePlaylistSong() {
        when(playlistRepository.findById(playlistId)).thenReturn(Optional.of(playlist));
        when(songRepository.findById(song1.getId())).thenReturn(Optional.of(song1));

        when(playlistSongsRepository.getPlaylistSongsByPlaylistAndSong(playlist, song1))
                .thenReturn(List.of(playlistSongs));

        doNothing().when(playlistSongsRepository).delete(playlistSongs);

        playlistService.deletePlaylistSong(song1.getId(), playlistId);

        verify(playlistSongsRepository, times(1)).delete(playlistSongs);
    }

    @Test
    public void itShouldThrowSongNotFoundInPlaylistException() {
        when(playlistRepository.findById(playlistId)).thenReturn(Optional.of(playlist));
        when(songRepository.findById(song2.getId())).thenReturn(Optional.of(song2));

        when(playlistSongsRepository.getPlaylistSongsByPlaylistAndSong(playlist, song2))
                .thenReturn(List.of());

        SongNotFoundException exception = Assertions.assertThrows(SongNotFoundInPlaylistException.class, () -> {
            playlistService.deletePlaylistSong(song2.getId(), playlistId);
        });

        assertEquals(SONG_NOT_FOUND_IN_PLAYLIST, exception.getMessage());
    }

}
