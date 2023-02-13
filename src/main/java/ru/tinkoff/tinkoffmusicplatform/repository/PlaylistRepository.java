package ru.tinkoff.tinkoffmusicplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinkoff.tinkoffmusicplatform.data.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

}
