package hu.zlaval.springcourse.book;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/books")
public class BookController {

    private final BookService service;

    @GetMapping
    public List<BookEntity> listBooks() {
        return service.findAll();
    }

    @PostMapping
    public BookEntity createBook(@RequestBody BookEntity book) {
        return service.save(book);
    }

}
