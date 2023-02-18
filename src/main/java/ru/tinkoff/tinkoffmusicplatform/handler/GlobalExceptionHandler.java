package ru.tinkoff.tinkoffmusicplatform.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.tinkoff.tinkoffmusicplatform.dto.response.ApplicationErrorDTO;
import ru.tinkoff.tinkoffmusicplatform.exception.SongNotFoundException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApplicationErrorDTO> catchResourceNotFoundException(
            SongNotFoundException e
    ) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ApplicationErrorDTO(BAD_REQUEST.value(), e.getMessage()), BAD_REQUEST);
    }
}
