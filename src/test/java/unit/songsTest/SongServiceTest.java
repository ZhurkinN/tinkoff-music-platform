package unit.songsTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.exception.SongNotFoundException;
import ru.tinkoff.tinkoffmusicplatform.repository.SongRepository;
import ru.tinkoff.tinkoffmusicplatform.service.impl.SongServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static ru.tinkoff.tinkoffmusicplatform.constants.ErrorMessageKeeper.*;

@ExtendWith(MockitoExtension.class)
public class SongServiceTest {

    private static final String NOT_EXISTING_TITLE = "Empty title";
    private static final String NOT_EXISTING_AUTHOR = "Empty author";
    private static final String NOT_EXISTING_GENRE = "Empty genre";
    private static final long NOT_EXISTING_ID = new Random().nextLong(100000000);
    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private SongServiceImpl songService;

    private Song song;

    private final long id;

    private String title;

    private String author;

    private String genre;

    {
        id = 1L;
        title = "Под небом салатовым";
        author = "ATL";
        genre = "Rap";
    }

    @BeforeEach
    public void setup() {

        song = Song.builder()
                .id(id)
                .title(title)
                .author(author)
                .genre(genre)
                .build();

    }

    @Test
    public void getAllSongsTest() {

        Song song1 = Song.builder()
                .id(2L)
                .title("Конечная станция")
                .author("Markul")
                .genre("Rap")
                .build();

        when(songRepository.findAll()).thenReturn(List.of(song, song1));

        List<Song> songList = songService.getAllSongs();

        assertThat(songList).isNotNull().isNotEmpty();
    }

    @Test
    public void getSongByIdTest() {

        when(songRepository.findById(id)).thenReturn(Optional.of(song));

        Song savedSong = songService.getSongById(song.getId());

        assertThat(savedSong).isNotNull();
    }

    @Test
    public void getSongsByTitle() {

        when(songRepository.findByTitle(title)).thenReturn(List.of(song));

        List<Song> songList = songService.getSongsByTitle(song.getTitle());

        assertThat(songList).isNotNull().isNotEmpty();
    }

    @Test
    public void getSongsByAuthor() {
        when(songRepository.findByAuthor(author)).thenReturn(List.of(song));

        List<Song> songList = songService.getSongsByAuthor(song.getAuthor());

        assertThat(songList).isNotNull().isNotEmpty();
    }

    @Test
    public void getSongsByGenreTest() {

        when(songRepository.findByGenre(genre)).thenReturn(List.of(song));

        List<Song> songList = songService.getSongsByGenre("Rap");

        assertThat(songList).isNotNull().isNotEmpty();
    }

    @Test
    public void saveSongTest() {

        when(songRepository.save(song)).thenReturn(song);

        Song savedSong = songService.save(song.getId(), song.getTitle(), song.getAuthor(), song.getGenre());

        assertEquals(song, savedSong);
        assertThat(savedSong).isNotNull();
    }

    @Test
    public void deleteSongByIdTest() {

        when(songRepository.findById(id)).thenReturn(Optional.of(song));
        doNothing().when(songRepository).delete(song);

        songService.deleteById(1L);

        verify(songRepository, times(0)).deleteById(song.getId());
    }

    @Test
    public void itShouldCatchSongNotFoundExceptionForFindingById() {
        when(songRepository.findById(anyLong())).thenReturn(Optional.empty());

        SongNotFoundException exception = Assertions.assertThrows(SongNotFoundException.class, () -> {
            Song song = songService.getSongById(NOT_EXISTING_ID);
        });
        verify(songRepository, times(1)).findById(anyLong());
        assertEquals(SONG_WITH_THIS_ID_NOT_FOUND, exception.getMessage());
    }

    @Test
    public void itShouldCatchSongNotFoundExceptionForFindingByTitle() {
        when(songRepository.findByTitle(NOT_EXISTING_TITLE)).thenReturn(List.of());

        SongNotFoundException exception = Assertions.assertThrows(SongNotFoundException.class, () -> {
            List<Song> songList = songService.getSongsByTitle(NOT_EXISTING_TITLE);
        });
        verify(songRepository, times(1)).findByTitle(NOT_EXISTING_TITLE);
        assertEquals(SONG_WITH_THIS_TITLE_NOT_FOUND, exception.getMessage());
    }

    @Test
    public void itShouldCatchSongNotFoundExceptionForFindingByAuthor() {
        when(songRepository.findByAuthor(NOT_EXISTING_AUTHOR)).thenReturn(List.of());

        SongNotFoundException exception = Assertions.assertThrows(SongNotFoundException.class, () -> {
            List<Song> songList = songService.getSongsByAuthor(NOT_EXISTING_AUTHOR);
        });
        verify(songRepository, times(1)).findByAuthor(NOT_EXISTING_AUTHOR);
        assertEquals(SONG_WITH_THIS_AUTHOR_NOT_FOUND, exception.getMessage());
    }

    @Test
    public void itShouldCatchSongNotFoundExceptionForFindingByGenre() {
        when(songRepository.findByGenre(NOT_EXISTING_GENRE)).thenReturn(List.of());

        SongNotFoundException exception = Assertions.assertThrows(SongNotFoundException.class, () -> {
            List<Song> songList = songService.getSongsByGenre(NOT_EXISTING_GENRE);
        });
        verify(songRepository, times(1)).findByGenre(NOT_EXISTING_GENRE);
        assertEquals(SONG_WITH_THIS_GENRE_NOT_FOUND, exception.getMessage());
    }

    @Test
    public void itShouldCatchSongNotFoundExceptionForDeleting() {
        when(songRepository.findById(anyLong())).thenReturn(Optional.empty());

        SongNotFoundException exception = Assertions.assertThrows(SongNotFoundException.class, () -> {
            songService.deleteById(NOT_EXISTING_ID);
        });
        verify(songRepository, times(1)).findById(anyLong());
        assertEquals(SONG_NOT_DELETED, exception.getMessage());
    }
}