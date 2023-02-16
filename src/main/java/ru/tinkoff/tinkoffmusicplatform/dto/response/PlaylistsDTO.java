package ru.tinkoff.tinkoffmusicplatform.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tinkoff.tinkoffmusicplatform.data.Playlist;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PlaylistsDTO {

    private List<Playlist> playlists;
}
