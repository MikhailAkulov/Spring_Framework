package ru.gb.myspringdemo.api;

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
public class UiController {

    @Autowired
    private BookService bookService;
    @Autowired
    private ReaderService readerService;
    @Autowired
    private IssueService issueService;

    //  должен выводить домашнюю страницу библиотеки
    @GetMapping
    public String home() {
        return "home";
    }

    //  /ui/books - на странице должен отобразиться список всех доступных книг в системе
    @GetMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.showAllBooks());
        return "books";
    }

    //  /ui/reader - на странице должен отобразиться список всех читателей в системе
    @GetMapping("/readers")
    public String getReaders(Model model) {
        model.addAttribute("readers", readerService.showAllReaders());
        return "readers";
    }

    //  /ui/issues - на странице отображается таблица, в которой есть столбцы (книга, читатель, когда взял, когда вернул
    //  (если не вернул - пустая ячейка)).
    @GetMapping("/issues")
    public String getIssues(Model model) {
        model.addAttribute("issues", issueService.showAllIssues());
        return "issues";
    }

    //  /ui/reader/{id} - страница, где написано имя читателя с идентификатором id и перечислены книги, которые на руках
    //  у этого читателя
    @GetMapping("/reader/{id}")
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
