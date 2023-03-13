package ru.tinkoff.tinkoffmusicplatform.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.tinkoff.tinkoffmusicplatform.dto.response.FileDTO;
import ru.tinkoff.tinkoffmusicplatform.dto.response.FilesPathDTO;
import ru.tinkoff.tinkoffmusicplatform.dto.response.ResponseMessageDTO;
import ru.tinkoff.tinkoffmusicplatform.service.MinioService;

import java.util.List;

import static ru.tinkoff.tinkoffmusicplatform.constants.ResultMessageKeeper.FILES_WERE_SAVED;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/files")
@RequiredArgsConstructor
public class MinioController {

    private final MinioService minioService;

    @GetMapping
    public ResponseEntity<List<FileDTO>> getFiles() {
        return ResponseEntity.ok(minioService.getListObjects());
    }

    @GetMapping("/{songId}")
    public ResponseEntity<FilesPathDTO> getSongFiles(@PathVariable Long songId) {

        FilesPathDTO responseDTO = new FilesPathDTO();
        try {
            String songPath = minioService.getSongsFilePath(songId);
            String picturePath = minioService.getSongsPicturePath(songId);
            responseDTO.setSongPath(songPath);
            responseDTO.setPicturePath(picturePath);
            return ResponseEntity.ok(responseDTO);

        } catch (Exception e) {
            responseDTO.setPicturePath(null);
            responseDTO.setSongPath(null);
            return ResponseEntity
                    .badRequest()
                    .body(responseDTO);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseMessageDTO> saveSongFiles(@RequestBody MultipartFile songFile,
                                                            @RequestBody MultipartFile pictureFile) {
        ResponseMessageDTO responseDTO = new ResponseMessageDTO();
        try {
            minioService.saveSongFiles(songFile, pictureFile);
            responseDTO.setMessage(FILES_WERE_SAVED);
            return ResponseEntity.ok(responseDTO);

        } catch (Exception e) {
            responseDTO.setMessage(e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(responseDTO);
        }
    }

}
