package ru.tinkoff.tinkoffmusicplatform.service;

import ru.tinkoff.tinkoffmusicplatform.dto.response.FileDTO;

import java.util.List;

public interface MinioService {

    List<FileDTO> getListObjects();

    void getSongFilesById(Long songId) throws Exception;

    String getPreSignedUrl(String fileName);

}
