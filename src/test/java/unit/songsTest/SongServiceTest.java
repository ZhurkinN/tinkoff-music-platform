package unit.songsTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.repository.SongRepository;
import ru.tinkoff.tinkoffmusicplatform.service.impl.SongServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SongServiceTest {

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

        assertThat(songList).isNotNull();
        assertThat(songList).isNotEmpty();
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

        assertThat(songList).isNotNull();
        assertThat(songList).isNotEmpty();
    }

    @Test
    public void getSongsByAuthor() {
        when(songRepository.findByAuthor(author)).thenReturn(List.of(song));

        List<Song> songList = songService.getSongsByAuthor(song.getAuthor());

        assertThat(songList).isNotNull();
        assertThat(songList).isNotEmpty();
    }

    @Test
    public void getSongsByGenreTest() {

        when(songRepository.findByGenre(genre)).thenReturn(List.of(song));

        List<Song> songList = songService.getSongsByGenre("Rap");

        assertThat(songList).isNotNull();
        assertThat(songList).isNotEmpty();
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
}