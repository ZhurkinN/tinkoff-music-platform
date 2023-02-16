package ru.tinkoff.tinkoffmusicplatform.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.tinkoffmusicplatform.data.Song;

import java.util.List;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {

    public Iterable<Song> findAllByOrderByGenre();

    public Iterable<Song> findByTitle(String title);

    public Iterable<Song> findByAuthor(String author);

    public Iterable<Song> findByGenre(String genre);

    @Query(value = "select song.* " +
            "from song " +
            "inner join playlist_songs ps on song.id = ps.song_id " +
            "where playlist_id = ?1",
            nativeQuery = true)
    List<Song> findSongByPlaylistId(Long playlistId);

}
