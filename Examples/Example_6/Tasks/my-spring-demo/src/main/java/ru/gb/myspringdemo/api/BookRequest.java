package ru.gb.myspringdemo.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BookRequest {

    @Schema(name = "Имя книги", minLength = 1)
    private String name;
}
