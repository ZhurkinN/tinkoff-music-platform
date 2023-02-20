package ru.tinkoff.tinkoffmusicplatform.dto.request;

public record SongBodyDTO(Long id,
                          String title,
                          String author,
                          String genre,
                          String fileName,
                          String pictureName) {
}
