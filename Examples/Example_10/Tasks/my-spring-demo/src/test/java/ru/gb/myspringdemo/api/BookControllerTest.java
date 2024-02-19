package ru.gb.myspringdemo.api;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.HttpClientErrorException;
import ru.gb.myspringdemo.JUnitSpringBootBase;
import ru.gb.myspringdemo.model.Book;
import ru.gb.myspringdemo.repository.BookRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @Test
    void testFindByIdSuccess() {
        Book expected = bookRepository.save(new Book(1L, "Book_1"));

        JUnitBookResponse responseBody = webTestClient.get()
                .uri("/book/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(JUnitBookResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getName(), responseBody.getName());
    }

    @Test
    void testFindByIdNotFound() {
        bookRepository.save(new Book(1L, "Book_1"));

        Long maxId = jdbcTemplate.queryForObject("select max(id) from books", Long.class);
        maxId++;

        webTestClient.get()
                .uri("/book/" + maxId)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testSaveBookSuccess() {
        JUnitBookResponse request = new JUnitBookResponse();
        request.setId(1L);
        request.setName("Book_1");

        JUnitBookResponse responseBody = webTestClient.post()
                .uri("/book")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(JUnitBookResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertNotNull(responseBody.getId());
        Assertions.assertTrue(bookRepository.findById(request.getId()).isPresent());
    }

    @Test
    void testDeleteBookSuccess() {
        bookRepository.saveAll(List.of(
                new Book(1L, "Book_1"),
                new Book(2L, "Book_2")
        ));
        Long deletedId = jdbcTemplate.queryForObject("select max(id) from books", Long.class);

        webTestClient.delete()
                .uri("/book/ " + deletedId)
                .exchange()
                .expectStatus().isOk();

        Assertions.assertFalse(bookRepository.existsById(deletedId));
    }

    @Test
    void testDeleteBookNotFound() {
        bookRepository.saveAll(List.of(
                new Book(1L, "Book_1"),
                new Book(2L, "Book_2")
        ));
        Long maxId = jdbcTemplate.queryForObject("select max(id) from books", Long.class);
        maxId++;

        webTestClient.delete()
                .uri("/book/ " + maxId)
                .exchange()
                .expectStatus().isNotFound();
    }
}