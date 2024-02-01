package ru.gb.myspringdemo.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Запрос на выдачу
 */
@Data
public class IssueRequest {

    @Schema(name = "Идентификатор читателя")
    private long readerId;

    @Schema(name = "Идентификатор книги")
    private long bookId;
}
