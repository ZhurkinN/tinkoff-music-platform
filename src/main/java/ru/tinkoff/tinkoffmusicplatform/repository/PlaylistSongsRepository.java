package ru.tinkoff.tinkoffmusicplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tinkoff.tinkoffmusicplatform.data.Playlist;
import ru.tinkoff.tinkoffmusicplatform.data.PlaylistSongs;
import ru.tinkoff.tinkoffmusicplatform.data.Song;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistSongsRepository extends JpaRepository<PlaylistSongs, Long> {

    @Query(value = "SELECT MAX(playlist_songs.song_position) " +
            "FROM playlist_songs " +
            "WHERE playlist_songs.playlist_id = ?1",
            nativeQuery = true)
    Integer getLastSongPosition(Long playlistId);

    List<PlaylistSongs> getPlaylistSongsByPlaylist(Playlist playlist);

    List<PlaylistSongs> getPlaylistSongsByPlaylistAndSong(Playlist playlist,
                                                          Song song);

    @Query(value = "select song_position " +
            "from playlist_songs " +
            "where song_id = ?1 and playlist_id = ?2",
            nativeQuery = true)
    Integer getSongPositionBySongAndPlaylist(Long songId,
                                             Long playlistId);

    Optional<PlaylistSongs> findBySongIdAndPlaylistId(Long songId,
                                                      Long playlistId);
}
