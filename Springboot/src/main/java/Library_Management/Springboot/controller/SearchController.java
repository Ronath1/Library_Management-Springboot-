package Library_Management.Springboot.controller;

import Library_Management.Springboot.entity.Book;
import Library_Management.Springboot.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public List<Book> searchBooks(@RequestParam String keyword) {
        return searchService.searchBooks(keyword);
    }
}
