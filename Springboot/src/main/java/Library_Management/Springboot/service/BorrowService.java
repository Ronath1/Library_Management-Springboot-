package Library_Management.Springboot.service;

import Library_Management.Springboot.entity.Borrow;
import Library_Management.Springboot.entity.Book;
import Library_Management.Springboot.entity.Member;
import Library_Management.Springboot.repository.BorrowRepository;
import Library_Management.Springboot.repository.BookRepository;
import Library_Management.Springboot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final FineService fineService;
    private final NotificationService notificationService; // Added

    @Transactional
    public Borrow borrowBook(Long memberId, Long bookId, int daysToKeep) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getQuantity() <= 0) {
            throw new RuntimeException("Book is out of stock!");
        }

        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        Borrow borrow = new Borrow();
        borrow.setMember(member);
        borrow.setBook(book);
        borrow.setBorrowDate(LocalDate.now());
        borrow.setDueDate(LocalDate.now().plusDays(daysToKeep));

        Borrow savedBorrow = borrowRepository.save(borrow);
        notificationService.createNotification("ISSUE: " + member.getName() + " borrowed '" + book.getTitle() + "'");
        return savedBorrow;
    }

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    @Transactional
    public void deleteBorrow(Long id) {
        Borrow borrow = borrowRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));

        Book book = borrow.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);

        borrowRepository.deleteById(id);
        notificationService.createNotification("REMOVED: Borrow record for book '" + book.getTitle() + "' deleted.");
    }

    @Transactional
    public void returnBook(Long borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        if (borrow.getReturnDate() != null) {
            throw new RuntimeException("This book has already been returned.");
        }

        borrow.setReturnDate(LocalDate.now());

        Book book = borrow.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);

        Borrow savedBorrow = borrowRepository.save(borrow);
        fineService.calculateAndSaveFine(savedBorrow);

        notificationService.createNotification("RETURN: " + borrow.getMember().getName() + " returned '" + book.getTitle() + "'");
    }
}

