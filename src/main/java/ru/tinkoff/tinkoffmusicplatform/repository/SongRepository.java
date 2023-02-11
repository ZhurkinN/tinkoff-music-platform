package ru.tinkoff.tinkoffmusicplatform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.tinkoffmusicplatform.data.Song;

@Repository
public interface SongRepository extends CrudRepository<Song, Integer> {


}
