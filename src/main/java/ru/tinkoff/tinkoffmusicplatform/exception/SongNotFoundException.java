package ru.tinkoff.tinkoffmusicplatform.exception;

public class SongNotFoundException extends RuntimeException {

    public SongNotFoundException(String message) {
        super(message);
    }
}
