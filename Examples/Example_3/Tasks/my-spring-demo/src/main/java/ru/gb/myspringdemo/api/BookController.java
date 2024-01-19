package ru.gb.myspringdemo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.myspringdemo.model.Book;
import ru.gb.myspringdemo.repository.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // GET  /book - получить список всех книг
    @GetMapping()
    public List<Book> getBooks() {
        return bookRepository.getAll();
    }

    //  GET /book/{id} - получить описание книги
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id) {
        return bookRepository.getBookById(id);
    }

    //  DELETE /book/{id} - удалить книгу
    @DeleteMapping("/{id}")
    public boolean deleteBookById(@PathVariable long id) {
        if (bookRepository.getBookById(id) == null) {
            throw new IllegalArgumentException();
        }
        bookRepository.deleteBookById(id);
        return true;
    }

    //  POST /book - создать книгу
    @PostMapping("/add")
    public Book addNewBook(@RequestBody Book book) {
        bookRepository.addBook(book);
        return book;
    }
}


