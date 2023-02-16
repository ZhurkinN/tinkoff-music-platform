package ru.tinkoff.tinkoffmusicplatform.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessageKeeper {

    public static final String PLAYLIST_NOT_CREATED = "Playlist wasn't created";
    public static final String SONG_OR_PLAYLIST_NOT_FOUND = "Song or playlist wasn't found";
    public static final String SONG_NOT_FOUND_IN_PLAYLIST = "Song wasn't found in playlist.";
}
