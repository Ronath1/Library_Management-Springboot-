package com.library.management.service;

import com.library.management.entity.Book;
import com.library.management.entity.Category;
import com.library.management.repository.BookRepository;
import com.library.management.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final NotificationService notificationService; // Added

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book, Long categoryId) {
        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
            book.setCategory(category);
        }
        Book savedBook = bookRepository.save(book);
        notificationService.createNotification("Added new book: " + savedBook.getTitle());
        return savedBook;
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot delete. Book not found with id: " + id));

        String title = book.getTitle();
        bookRepository.deleteById(id);
        notificationService.createNotification("Deleted book: " + title);
    }

    public Book updateBook(Long id, Book bookDetails, Long categoryId) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        existingBook.setTitle(bookDetails.getTitle());
        existingBook.setAuthor(bookDetails.getAuthor());
        existingBook.setIsbn(bookDetails.getIsbn());
        existingBook.setQuantity(bookDetails.getQuantity());

        if (categoryId != null) {
            Category newCategory = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category " + categoryId + " not found"));
            existingBook.setCategory(newCategory);
        }

        Book updatedBook = bookRepository.save(existingBook);
        notificationService.createNotification("Updated book details: " + updatedBook.getTitle());
        return updatedBook;
    }
}
