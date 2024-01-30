package ru.gb.myspringdemo.api;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.myspringdemo.model.Issue;
import ru.gb.myspringdemo.model.Reader;
import ru.gb.myspringdemo.repository.ReaderRepository;
import ru.gb.myspringdemo.service.IssueService;
import ru.gb.myspringdemo.service.ReaderService;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/reader")
public class ReaderController {

    private final ReaderRepository readerRepository;

    @Autowired
    private ReaderService readerService;
    @Autowired
    private IssueService issueService;

    public ReaderController(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    // GET  /reader - получить список всех читателей
    @GetMapping()
    public ResponseEntity<List<Reader>> getAllReaders() {
        log.info("Получен запрос актуального списка читателей");

        return new ResponseEntity<>(readerService.showAllReaders(), HttpStatus.OK);
    }

    //  GET /reader/{id} - получить описание читателя
    @GetMapping("/{id}")
    public ResponseEntity<Reader> getReaderInfo(@PathVariable long id) {
        log.info("Получен запрос информации о читателе: Id = {}", id);

        final Reader reader;
        try {
            reader = readerService.showReaderInfo(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(reader);
    }

    //  DELETE /reader/{id} - удалить читателя
    @DeleteMapping("/{id}")
    public ResponseEntity<Reader> deleteReader(@PathVariable long id) {
        log.info("Получен запрос на удаление читателя: Id = {}", id);

        final Reader reader;
        try {
            reader = readerService.deleteReader(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(reader);
    }

    //  POST /reader - создать читателя
    @PostMapping()
    public ResponseEntity<Reader> addNewReader(@RequestBody ReaderRequest request) {
        log.info("Получен запрос на добавление читателя: name = {}", request.getName());

        final Reader reader;
        try {
            reader = readerService.addNewReader(request);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(reader);
    }

    @GetMapping("/{id}/issue")
    public ResponseEntity<List<Issue>> getReaderIssues(@PathVariable long id) {
        log.info("Получен запрос информации о выдачах читателя с id = {}", id);

        final List<Issue> readersIssues;
        try {
            readersIssues = issueService.getAllIssuesByReader(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(readersIssues);
    }

    @PostConstruct
    public void generateData() {
        readerRepository.save(new Reader("Читатель1"));
        readerRepository.save(new Reader("Читатель2"));
        readerRepository.save(new Reader("Читатель3"));
        readerRepository.save(new Reader("Читатель4"));
        readerRepository.save(new Reader("Читатель5"));
    }
}
