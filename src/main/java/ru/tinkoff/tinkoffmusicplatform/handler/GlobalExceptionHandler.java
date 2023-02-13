package ru.tinkoff.tinkoffmusicplatform.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.tinkoff.tinkoffmusicplatform.data.ApplicationError;
import ru.tinkoff.tinkoffmusicplatform.exception.SongNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApplicationError> catchResourceNotFoundException(
            SongNotFoundException e
    ) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ApplicationError(NOT_FOUND.value(), e.getMessage()), NOT_FOUND);
    }
}
