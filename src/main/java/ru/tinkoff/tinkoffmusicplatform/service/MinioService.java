package ru.tinkoff.tinkoffmusicplatform.service;

import org.springframework.web.multipart.MultipartFile;
import ru.tinkoff.tinkoffmusicplatform.dto.response.FileDTO;

import java.util.List;

public interface MinioService {

    List<FileDTO> getListObjects();

    String getSongsPicturePath(Long songId) throws Exception;

    String getSongsFilePath(Long songId) throws Exception;

    void saveSongFiles(MultipartFile songFile,
                       MultipartFile pictureFile) throws Exception;

    String getPreSignedUrl(String fileName);

}
