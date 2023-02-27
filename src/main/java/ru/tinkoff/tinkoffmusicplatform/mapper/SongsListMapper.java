package ru.tinkoff.tinkoffmusicplatform.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.dto.response.SongListDTO;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SongsListMapper {

    public static List<SongListDTO> mapSongsListToDTO(List<Song> songs) {
        List<SongListDTO> dtoList = new ArrayList<>();
        for (Song song : songs) {
            SongListDTO dto = new SongListDTO()
                    .setId(song.getId())
                    .setTitle(song.getTitle())
                    .setAuthor(song.getAuthor())
                    .setGenre(song.getGenre());
            dtoList.add(dto);
        }
        return dtoList;
    }
}
