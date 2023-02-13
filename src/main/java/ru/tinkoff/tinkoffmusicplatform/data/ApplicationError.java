package ru.tinkoff.tinkoffmusicplatform.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationError {

    private int statusCode;

    private String message;

}
