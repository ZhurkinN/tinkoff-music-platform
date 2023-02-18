package ru.tinkoff.tinkoffmusicplatform.service.impl;

import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tinkoff.tinkoffmusicplatform.dto.response.FileDTO;
import ru.tinkoff.tinkoffmusicplatform.service.MinioService;

import java.util.ArrayList;
import java.util.List;

import static ru.tinkoff.tinkoffmusicplatform.constants.ErrorMessageKeeper.MINIO_OBJECTS_ERROR;

@Slf4j
@Service
public class MinioServiceImpl implements MinioService {

    private final MinioClient minioClient;

    @Value("${minio.bucket.name}")
    private String bucketName;

    public MinioServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }


    @Override
    public List<FileDTO> getListObjects() {
        List<FileDTO> objects = new ArrayList<>();
        try {
            Iterable<Result<Item>> result = minioClient.listObjects(ListObjectsArgs.builder()
                    .bucket(bucketName)
                    .recursive(true)
                    .build());
            for (Result<Item> item : result) {
                objects.add(FileDTO.builder()
                        .filename(item.get().objectName())
                        .size(item.get().size())
                        .url(getPreSignedUrl(item.get().objectName()))
                        .build());
            }
            return objects;
        } catch (Exception e) {
            log.error(MINIO_OBJECTS_ERROR, e);
        }

        return objects;
    }

    @Override
    public String getPreSignedUrl(String filename) {
        return "http://localhost:8080/file/".concat(filename);
    }
}
