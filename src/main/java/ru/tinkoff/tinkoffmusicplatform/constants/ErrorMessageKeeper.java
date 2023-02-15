package ru.tinkoff.tinkoffmusicplatform.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessageKeeper {

    public static final String PLAYLIST_WASNT_CREATED = "Playlist wasn't created";
    public static final String SONG_WITH_THIS_AUTHOR_NOT_FOUND = "Song with this author wasn't found";
    public static final String SONG_WITH_THIS_TITLE_NOT_FOUND = "Song with this title wasn't found";
    public static final String SONG_WITH_THIS_GENRE_NOT_FOUND = "Song with this title wasn't found";
    public static final String SONG_WITH_THIS_ID_NOT_FOUND = "Song with this id wasn't found";
    public static final String SONG_CANNOT_BE_DELETED = "Song cannot be deleted";

}
