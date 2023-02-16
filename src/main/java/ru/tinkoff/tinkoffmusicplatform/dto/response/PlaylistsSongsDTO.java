package ru.tinkoff.tinkoffmusicplatform.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.tinkoff.tinkoffmusicplatform.data.Song;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class PlaylistsSongsDTO {

    private Song song;
    private Integer songPosition;
}
