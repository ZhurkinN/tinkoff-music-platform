package ru.tinkoff.tinkoffmusicplatform.service.impl;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.dto.response.FileDTO;
import ru.tinkoff.tinkoffmusicplatform.repository.SongRepository;
import ru.tinkoff.tinkoffmusicplatform.service.MinioService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.tinkoff.tinkoffmusicplatform.constants.ErrorMessageKeeper.MINIO_OBJECTS_ERROR;
import static ru.tinkoff.tinkoffmusicplatform.constants.ErrorMessageKeeper.SONG_WITH_THIS_ID_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {

    private final MinioClient minioClient;
    private final SongRepository songRepository;

    @Value("${minio.bucket.name}")
    private String bucketName;

    @Override
    public List<FileDTO> getListObjects() {
        List<FileDTO> objects = new ArrayList<>();
        try {
            Iterable<Result<Item>> result = minioClient.listObjects(ListObjectsArgs.builder()
                    .bucket(bucketName)
                    .recursive(true)
                    .build());
            for (Result<Item> item : result) {
                FileDTO file = new FileDTO()
                        .setFilename(item.get().objectName())
                        .setSize(item.get().size())
                        .setUrl(getPreSignedUrl(item.get().objectName()));

                objects.add(file);
            }
            return objects;
        } catch (Exception e) {
            log.error(MINIO_OBJECTS_ERROR, e);
        }

        return objects;
    }

    @Override
    public String getSongsPicturePath(Long songId) throws Exception {
        Optional<Song> song = songRepository.findById(songId);
        String pictureName;
        if (song.isPresent()) {
            pictureName = song.get().getPictureName();
        } else {
            throw new FileNotFoundException(SONG_WITH_THIS_ID_NOT_FOUND);
        }
        return getFilePath(pictureName);
    }

    @Override
    public String getSongsFilePath(Long songId) throws Exception {
        Optional<Song> song = songRepository.findById(songId);
        String fileName;
        if (song.isPresent()) {
            fileName = song.get().getFileName();
        } else {
            throw new FileNotFoundException(SONG_WITH_THIS_ID_NOT_FOUND);
        }
        return getFilePath(fileName);
    }

    @Override
    public void saveSongFiles(MultipartFile songInputFile, MultipartFile pictureInputFile) throws Exception {
        putFile(songInputFile);
        putFile(pictureInputFile);
    }


    @Override
    public String getPreSignedUrl(String fileName) {
        return "http://localhost:8080/file/".concat(fileName);
    }

    private String getFilePath(String fileName) {
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build())) {

            String path = "src/main/resources/songs/" + fileName;
            File targetFile = new File(path);
            FileUtils.copyInputStreamToFile(stream, targetFile);
            return path;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void putFile(MultipartFile file) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(file.getOriginalFilename())
                        .stream(file.getInputStream(), -1, 10485760)
                        .contentType(file.getContentType())
                        .build());
    }
}
