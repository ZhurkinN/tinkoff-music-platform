package ru.tinkoff.tinkoffmusicplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.tinkoffmusicplatform.data.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {


}
