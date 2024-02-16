package ru.gb.myspringdemo.api;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.myspringdemo.JUnitSpringBootBase;
import ru.gb.myspringdemo.model.Book;
import ru.gb.myspringdemo.repository.BookRepository;

import java.util.List;
import java.util.Objects;

class BookControllerTest extends JUnitSpringBootBase {

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Data
    static class JUnitBookResponse {
        private Long id;
        private String name;
    }

    @BeforeEach
    void clean() {
        bookRepository.deleteAll();
    }

    @Test
    void testGetAllBooks() {
        bookRepository.saveAll(List.of(
                new Book(1L, "Book_1"),
                new Book(2L, "Book_2")
        ));

        List<Book> expected = bookRepository.findAll();

        List<JUnitBookResponse> responseBody = webTestClient.get()
                .uri("/book")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<JUnitBookResponse>>() {
                })
                .returnResult()
                .getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.size(), responseBody.size());
        for (JUnitBookResponse bookResponse : responseBody) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(),bookResponse.getId()))
                    .anyMatch(it -> Objects.equals(it.getName(), bookResponse.getName()));
            Assertions.assertTrue(found);
        }
    }

}