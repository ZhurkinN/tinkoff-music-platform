package ru.tinkoff.tinkoffmusicplatform.data;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Objects;

@Entity
@Table(name = "playlist_songs")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PlaylistSongs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "song_position")
    private Integer songPosition;

    @OneToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Song song;

    @OneToOne
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Playlist playlist;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PlaylistSongs that = (PlaylistSongs) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
