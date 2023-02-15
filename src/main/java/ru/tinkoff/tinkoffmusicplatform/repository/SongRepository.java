package ru.tinkoff.tinkoffmusicplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.tinkoffmusicplatform.data.Song;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findAllByOrderByGenre();

    List<Song> findByTitle(String title);

    List<Song> findByAuthor(String author);

    List<Song> findByGenre(String genre);

}
