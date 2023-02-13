package ru.tinkoff.tinkoffmusicplatform.data;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "playlist")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

}
