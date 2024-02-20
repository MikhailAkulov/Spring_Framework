package ru.geekbrains;

import lombok.Data;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

@Endpoint(id = "MyCustomEndpoint")
public class MyCustomEndpoint {

    @ReadOperation
    public CustomResponse customMethod() {
        return new CustomResponse(/*"Everything is awesome!", 42*/);
    }

    @Data
    public static class CustomResponse {
        private String message;
        private int number;
        // getters and setters
    }
}
