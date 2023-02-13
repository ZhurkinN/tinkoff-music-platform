package ru.tinkoff.tinkoffmusicplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.tinkoffmusicplatform.data.PlaylistSongs;

@Repository
public interface PlayListSongs extends JpaRepository<PlaylistSongs, Long> {

}
