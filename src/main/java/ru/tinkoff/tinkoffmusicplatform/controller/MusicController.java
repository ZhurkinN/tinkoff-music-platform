package ru.tinkoff.tinkoffmusicplatform.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/musics")
public class MusicController {

    @GetMapping
    public ResponseEntity<String> getMusic() {
        return ResponseEntity.ok("Music plays");
    }
}
