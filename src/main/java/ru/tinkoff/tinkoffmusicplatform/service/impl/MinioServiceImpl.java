package ru.tinkoff.tinkoffmusicplatform.service.impl;

import io.minio.GetObjectArgs;
import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tinkoff.tinkoffmusicplatform.data.Song;
import ru.tinkoff.tinkoffmusicplatform.dto.response.FileDTO;
import ru.tinkoff.tinkoffmusicplatform.repository.SongRepository;
import ru.tinkoff.tinkoffmusicplatform.service.MinioService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    public void getSongFilesById(Long songId) throws FileNotFoundException {

        Optional<Song> song = songRepository.findById(songId);
        String fileName;
        String pictureName;

        if (song.isPresent()) {
            fileName = song.get().getFileName();
            pictureName = song.get().getPictureName();
        } else {
            throw new FileNotFoundException(SONG_WITH_THIS_ID_NOT_FOUND);
        }

        getFile(fileName);
        getFile(pictureName);
    }

    @Override
    public String getPreSignedUrl(String fileName) {
        return "http://localhost:8080/file/".concat(fileName);
    }

    private void getFile(String fileName) {
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build())) {

            File targetFile = new File("src/main/resources/songs/" + fileName);
            FileUtils.copyInputStreamToFile(stream, targetFile);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
