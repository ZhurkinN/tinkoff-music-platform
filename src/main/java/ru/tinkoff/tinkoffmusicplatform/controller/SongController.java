import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.service.SongService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/songs")
public class SongController {

    private final SongService songService;

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        return ResponseEntity.ok(songService.getSongById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Song>> getSongs() {
        return ResponseEntity.ok(songService.getAllSongs());
    }
}