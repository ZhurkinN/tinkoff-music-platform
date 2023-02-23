package unit.songsTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Service;
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

    @BeforeEach
    public void setup() {
        song = Song.builder()
                .id(1L)
                .title("Под небом салатовым")
                .author("ATL")
                .genre("Rap")
                .build();
    }

    @Test
    public void getSongByIdTest() {

        when(songRepository.findById(1L)).thenReturn(Optional.of(song));

        Song savedSong = songService.getSongById(song.getId());

        assertThat(savedSong).isNotNull();
    }

    @Test
    public void getSongByGenreTest() {

        when(songRepository.findByGenre("Rap")).thenReturn(List.of(song));

        List<Song> songList = songService.getSongsByGenre("Rap");

        assertThat(songList).isNotNull();

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

    }

    @Test
    public void saveSongTest() {

        when(songRepository.save(song)).thenReturn(song);

        Song savedSong = songService.save(song.getId(), song.getTitle(), song.getAuthor(), song.getGenre());

        assertEquals(song, savedSong);
    }


    // Тут момент с тем, что мы удаляем через дефолтный метод репозитория, а не через deleteById
    @Test
    public void deleteSongByIdTest() {

        when(songRepository.findById(1L)).thenReturn(Optional.of(song));
        doNothing().when(songRepository).delete(song);

        songService.deleteById(1L);

        verify(songRepository, times(0)).deleteById(song.getId());

    }
}