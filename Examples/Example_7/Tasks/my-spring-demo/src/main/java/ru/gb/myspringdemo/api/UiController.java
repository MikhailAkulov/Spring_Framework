package ru.gb.myspringdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.myspringdemo.model.Book;
import ru.gb.myspringdemo.model.Issue;
import ru.gb.myspringdemo.model.Reader;
import ru.gb.myspringdemo.service.BookService;
import ru.gb.myspringdemo.service.IssueService;
import ru.gb.myspringdemo.service.ReaderService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ui")
@Tag(name = "UI")
public class UiController {

    @Autowired
    private BookService bookService;
    @Autowired
    private ReaderService readerService;
    @Autowired
    private IssueService issueService;

    // GET  /ui
    @GetMapping
    @Operation(summary = "go home", description = "Загружает домашнюю страницу в браузере")
    public String home() {
        return "home";
    }

    // GET  /ui/books
    @GetMapping("/books")
    @Operation(summary = "get list of all books", description = "Загружает страницу со списком книг, внесённых в систему")
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.showAllBooks());
        return "books";
    }

    // GET  /ui/reader
    @GetMapping("/readers")
    @Operation(summary = "get list of all readers", description = "Загружает страницу со списком читателей, зарегистрированных в системе")
    public String getReaders(Model model) {
        model.addAttribute("readers", readerService.showAllReaders());
        return "readers";
    }

    // GET  /ui/issues
    @GetMapping("/issues")
    @Operation(summary = "get table about all book issuance's", description = "Загружает страницу с таблицей " +
            "с информацией о всех выдачах книг читателям")
    public String getIssues(Model model) {
        model.addAttribute("issues", issueService.showAllIssues());
        return "issues";
    }

    // GET  /ui/reader/{id} - страница, где написано имя читателя с идентификатором id и перечислены книги
    @GetMapping("/reader/{id}")
    @Operation(summary = "get issuance list by reader", description = "Загружает страницу со списком всех книг, " +
            "когда-либо выданных читателю")
    public String getIssuesByReaderId(@PathVariable long id, Model model) {
        Reader reader = readerService.showReaderInfo(id);
        List<Issue> issues = issueService.getAllIssuesByReader(id);
        List<Book> books = new ArrayList<>();
        for (Issue issue : issues) {
            books.add(bookService.showBookInfo(issue.getBookId()));
        }
        model.addAttribute("books", books);
        model.addAttribute("reader", reader);
        model.addAttribute("issues", issues);
        return "booksByReader";
    }

}