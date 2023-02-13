package ru.tinkoff.tinkoffmusicplatform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.tinkoffmusicplatform.data.Song;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {

    public Iterable<Song> findAllByOrderByGenre();

    public Iterable<Song> findByTitle(String title);

    public Iterable<Song> findByAuthor(String author);

    public Iterable<Song> findByGenre(String genre);

}
