package ru.tinkoff.tinkoffmusicplatform.service;

import ru.tinkoff.tinkoffmusicplatform.dto.response.FileDTO;

import java.util.List;

public interface MinioService {

    List<FileDTO> getListObjects();

    String getPreSignedUrl(String filename);

}
