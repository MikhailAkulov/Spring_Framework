package ru.gb.myspringdemo.api;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.myspringdemo.model.Issue;
import ru.gb.myspringdemo.repository.IssueRepository;
import ru.gb.myspringdemo.service.IssueService;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
public class IssueController {

    private final IssueRepository issueRepository;

    @Autowired
    private IssueService service;

    public IssueController(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @PostMapping
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
        log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

        final Issue issue;
        try {
            issue = service.issue(request);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(issue);
    }

    //  GET /issue/{id} - получить описание факта выдачи
    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssueInfo(@PathVariable long id) {
        log.info("Получен запрос на описание факта выдачи: id = {}", id);

        final Issue issue;
        try {
            issue = service.showIssueInfo(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(issue);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Issue>> getAllIssues() {
        log.info("Получен запрос актуального списка всех выдач книг");

        return new ResponseEntity<>(service.showAllIssues(), HttpStatus.OK);
    }

    //  PUT /issue/{issueId} - закрывает факт выдачи.
    @PutMapping("/{issueId}")
    public ResponseEntity<Issue> returnBook(@PathVariable long issueId) {
        log.info("Получен запрос на возврат книги по выдаче с id = {}", issueId);

        final Issue issue;
        try {
            issue = service.returnBook(issueId);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(issue);

    }

    @PostConstruct
    public void generateData() {
        issueRepository.save(new Issue(1, 1));
        issueRepository.save(new Issue(3, 1));
        issueRepository.save(new Issue(2, 2));
        issueRepository.save(new Issue(4, 2));
        issueRepository.save(new Issue(5, 3));
        issueRepository.save(new Issue(3, 3));
        issueRepository.save(new Issue(4, 4));
        issueRepository.save(new Issue(2, 4));
        issueRepository.save(new Issue(3, 5));
        issueRepository.save(new Issue(1, 5));
    }
}
