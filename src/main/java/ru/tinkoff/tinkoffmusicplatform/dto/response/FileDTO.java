package ru.tinkoff.tinkoffmusicplatform.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Accessors(chain = true)
public class FileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 232836038145089522L;

    private String title;

    private String description;

    @SuppressWarnings("java:S1948")
    private MultipartFile file;

    private String url;

    private Long size;

    private String filename;

}
