package ru.tinkoff.tinkoffmusicplatform.data;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "playlist_songs")
@Getter
@Setter
@Accessors(chain = true)
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
public class PlaylistSongs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "song_position")
    private Integer songPosition;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Song song;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Playlist playlist;

}
