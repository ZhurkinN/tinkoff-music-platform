package ru.tinkoff.tinkoffmusicplatform.exception;

public class SongNotFoundInPlaylistException extends SongNotFoundException {

    public SongNotFoundInPlaylistException(String message) {
        super(message);
    }
}
