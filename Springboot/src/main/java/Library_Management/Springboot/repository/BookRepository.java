package Library_Management.Springboot.repository;

import Library_Management.Springboot.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    List<Book> findByTitleContainingIgnoreCase(String keyword);
}
