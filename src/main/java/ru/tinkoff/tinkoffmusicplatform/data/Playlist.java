package ru.tinkoff.tinkoffmusicplatform.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "playlist")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Playlist playlist = (Playlist) o;
        return id != null && Objects.equals(id, playlist.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}