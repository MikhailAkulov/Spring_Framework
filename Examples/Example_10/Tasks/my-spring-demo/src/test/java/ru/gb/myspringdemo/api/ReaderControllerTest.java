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
import ru.gb.myspringdemo.model.Reader;
import ru.gb.myspringdemo.repository.ReaderRepository;

import java.util.List;
import java.util.Objects;

class ReaderControllerTest extends JUnitSpringBootBase {

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    ReaderRepository readerRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Data
    static class JUnitReaderResponse {
        private Long id;
        private String name;
    }

    @BeforeEach
    void clean() {
        readerRepository.deleteAll();
    }

    @Test
    void testGetAllReaders() {
        readerRepository.saveAll(List.of(
                new Reader(1L, "Reader_1"),
                new Reader(2L, "Reader_2")
        ));

        List<Reader> expected = readerRepository.findAll();

        List<JUnitReaderResponse> responseBody = webTestClient.get()
                .uri("/reader")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<JUnitReaderResponse>>() {})
                .returnResult()
                .getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.size(), responseBody.size());
        for (JUnitReaderResponse readerResponse : responseBody) {
            boolean found = expected.stream()
                    .filter(it -> Objects.equals(it.getId(), readerResponse.getId()))
                    .anyMatch(it -> Objects.equals(it.getName(), readerResponse.getName()));
            Assertions.assertTrue(found);
        }
    }

    @Test
    void testFindByIdSuccess() {
        Reader expected = readerRepository.save(new Reader(1L, "Reader_1"));

        JUnitReaderResponse responseBody = webTestClient.get()
                .uri("/reader/" + expected.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(JUnitReaderResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(expected.getId(), responseBody.getId());
        Assertions.assertEquals(expected.getName(), responseBody.getName());
    }

    @Test
    void testFindByIdNotFound() {
        readerRepository.save(new Reader(1L, "Reader_1"));

        Long nonExisting = jdbcTemplate.queryForObject("select max(id) from readers", Long.class);
        nonExisting++;

        webTestClient.get()
                .uri("/reader/" + nonExisting)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testSaveReaderSuccess() {
        JUnitReaderResponse request = new JUnitReaderResponse();
        request.setId(1L);
        request.setName("Reader_1");

        JUnitReaderResponse responseBody = webTestClient.post()
                .uri("/reader")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(JUnitReaderResponse.class)
                .returnResult().getResponseBody();

        Assertions.assertNotNull(responseBody);
        Assertions.assertNotNull(responseBody.getId());
        Assertions.assertTrue(readerRepository.findById(request.getId()).isPresent());
    }

    @Test
    void testSaveReaderFail() {
        webTestClient.post()
                .uri("reader")
                .bodyValue(readerRepository.save(new Reader()))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody(IllegalArgumentException.class);
    }

    @Test
    void testDeleteReaderSuccess() {
        readerRepository.saveAll(List.of(
                new Reader(1L, "Reader_1"),
                new Reader(2L, "Reader_2")
        ));

        Long deletedId = jdbcTemplate.queryForObject("select max(id) from readers", Long.class);

        webTestClient.delete()
                .uri("/reader/" + deletedId)
                .exchange()
                .expectStatus().isOk();

        Assertions.assertFalse(readerRepository.existsById(deletedId));
    }

    @Test
    void testDeleteReaderNotFound() {
        readerRepository.saveAll(List.of(
                new Reader(1L, "Reader_1"),
                new Reader(2L, "Reader_2")
        ));

        Long nonExisting = jdbcTemplate.queryForObject("select max(id) from readers", Long.class);
        nonExisting++;

        webTestClient.delete()
                .uri("/reader/" + nonExisting)
                .exchange()
                .expectStatus().isNotFound();
    }
}