package Library_Management.Springboot.repository;

import Library_Management.Springboot.entity.Borrow;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {


    long count();

    @Query("SELECT b.member.id FROM Borrow b GROUP BY b.member.id ORDER BY COUNT(b.member.id) DESC")
    List<Long> findTopMemberIds(Pageable pageable);

    @Query("SELECT b.book.id FROM Borrow b GROUP BY b.book.id ORDER BY COUNT(b.book.id) DESC")
    List<Long> findTopBookIds(Pageable pageable);
}

