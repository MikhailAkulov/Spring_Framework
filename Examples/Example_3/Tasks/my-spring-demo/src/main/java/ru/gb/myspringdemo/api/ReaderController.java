package ru.gb.myspringdemo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.myspringdemo.model.Reader;
import ru.gb.myspringdemo.repository.ReaderRepository;

import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    private final ReaderRepository readerRepository;

    @Autowired
    public ReaderController(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    // GET  /reader - получить список всех читателей
    @GetMapping()
    public List<Reader> getReaders() {
        return readerRepository.getAll();
    }

    //  GET /reader/{id} - получить описание читателя
    @GetMapping("/{id}")
    public Reader getReaderById(@PathVariable long id) {
        return readerRepository.getReaderById(id);
    }

    //  DELETE /reader/{id} - удалить читателя
    @DeleteMapping("/{id}")
    public boolean deleteReaderById(@PathVariable long id) {
        if (readerRepository.getReaderById(id) == null) {
            throw new IllegalArgumentException();
        }
        readerRepository.deleteReaderById(id);
        return true;
    }

    //  POST /reader - создать читателя
    //  {
    //    "id": 4,
    //    "name": "Читатель4"
    //  }
    @PostMapping("/add")
    public Reader addNewReader(@RequestBody Reader reader) {
        readerRepository.addReader(reader);
        return reader;
    }
}
