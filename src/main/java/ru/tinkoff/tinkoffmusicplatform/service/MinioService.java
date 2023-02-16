package ru.tinkoff.tinkoffmusicplatform.service;

import ru.tinkoff.tinkoffmusicplatform.dto.request.FileDto;

import java.util.List;

public interface MinioService {

    List<FileDto> getListObjects();

    String getPreSignedUrl(String filename);

}
