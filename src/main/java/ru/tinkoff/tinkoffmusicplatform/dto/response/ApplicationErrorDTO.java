package ru.tinkoff.tinkoffmusicplatform.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationErrorDTO {

    private int statusCode;

    private String message;

}
