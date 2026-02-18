package Library_Management.Springboot.service;

import Library_Management.Springboot.entity.Book;
import Library_Management.Springboot.entity.Category;
import Library_Management.Springboot.repository.BookRepository;
import Library_Management.Springboot.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book, Long categoryId) {
        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
            book.setCategory(category);
        }
        return bookRepository.save(book);
    }


    public Book getBookById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }



    public void deleteBook(Long id) {
        if(!bookRepository.existsById(id)){
            throw new RuntimeException("Cannot delete. Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
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

            existingBook.setCategory(newCategory); // <--- This links them!
        }

        return bookRepository.save(existingBook);
    }



}
