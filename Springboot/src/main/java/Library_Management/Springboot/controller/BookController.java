package Library_Management.Springboot.controller;

import Library_Management.Springboot.entity.Book;
import Library_Management.Springboot.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }


    @PostMapping
    public Book addBook(@RequestBody Book book, @RequestParam(name = "categoryId", required = false) Long categoryId) {
        return bookService.addBook(book, categoryId);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);

    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }


    @PutMapping("/{id}")
    public Book updateBook(
            @PathVariable Long id,
            @RequestBody Book bookDetails,
            @RequestParam(name = "categoryId", required = false) Long categoryId) { // Added required = false
        return bookService.updateBook(id, bookDetails, categoryId);
    }
}
