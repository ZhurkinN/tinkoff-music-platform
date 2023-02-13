package ru.tinkoff.tinkoffmusicplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.tinkoffmusicplatform.data.Playlist;

@Repository
public interface PlayListRepository extends JpaRepository<Playlist, Long> {

}
