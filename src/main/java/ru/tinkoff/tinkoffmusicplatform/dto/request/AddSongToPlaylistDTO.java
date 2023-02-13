package ru.tinkoff.tinkoffmusicplatform.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddSongToPlaylistDTO {

    private Long songId;
    private Long playlistId;
}
