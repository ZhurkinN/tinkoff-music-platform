package ru.tinkoff.tinkoffmusicplatform.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessageKeeper {

    public static final String SONG_WITH_THIS_AUTHOR_NOT_FOUND = "Song with this author wasn't found.";
    public static final String SONG_WITH_THIS_TITLE_NOT_FOUND = "Song with this title wasn't found.";
    public static final String SONG_WITH_THIS_GENRE_NOT_FOUND = "Song with this title wasn't found.";
    public static final String SONG_WITH_THIS_ID_NOT_FOUND = "Song with this id wasn't found.";
    public static final String SONG_NOT_DELETED = "Song wasn't deleted.";
    public static final String SONG_NOT_ADDED = "Song wasn't created.";
    public static final String SONG_ALREADY_ADDED = "This song is already in playlist.";

    public static final String PLAYLIST_NOT_CREATED = "Playlist wasn't created.";
    public static final String SONG_OR_PLAYLIST_NOT_FOUND = "Song or playlist wasn't found.";
    public static final String SONG_NOT_FOUND_IN_PLAYLIST = "Song wasn't found in playlist.";

    public static final String MINIO_OBJECTS_ERROR = "Happened error when get list objects from minio: ";
}
