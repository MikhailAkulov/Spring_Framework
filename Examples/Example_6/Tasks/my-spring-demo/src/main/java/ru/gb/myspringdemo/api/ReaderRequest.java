package ru.gb.myspringdemo.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReaderRequest {

    @Schema(name = "Имя читателя", minLength = 1)
    private String name;
}
