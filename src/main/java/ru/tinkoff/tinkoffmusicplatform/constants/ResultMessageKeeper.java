package ru.tinkoff.tinkoffmusicplatform.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResultMessageKeeper {

    public static final String PLAYLIST_WAS_CREATED = "Playlist was created successfully!";
    public static final String SONG_WAS_ADDED_TO_PLAYLIST = "Song was added to playlist!";
    public static final String SONG_WAS_DELETED_FROM_PLAYLIST = "Song was deleted from playlist!";

    public static final String SONG_WAS_CREATED = "Song was created";
    public static final String SONG_WAS_DELETED = "Song was deleted";
    public static final String FILES_WERE_SAVED = "Files were saved successfully!";
}
