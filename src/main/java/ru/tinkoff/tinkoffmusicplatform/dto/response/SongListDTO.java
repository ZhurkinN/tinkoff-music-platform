package ru.tinkoff.tinkoffmusicplatform.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class SongListDTO {

    private Long id;
    private String title;
    private String author;
    private String genre;
    private String picturePath;
    private String songPath;
}
