package Library_Management.Springboot.controller;

import Library_Management.Springboot.entity.Borrow;
import Library_Management.Springboot.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
public class DatabaseTestController {

    @Autowired
    private BorrowRepository borrowRepository;

    // Try a simpler URL: http://localhost:8081/make-late/1
    @GetMapping("/make-late/{id}")
    public String makeLate(@PathVariable Long id) {
        Borrow borrow = borrowRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrow not found with ID: " + id));

        borrow.setDueDate(LocalDate.now().minusDays(10)); // Force 10 days ago
        borrowRepository.save(borrow);

        return "SUCCESS: Borrow ID " + id + " is now overdue. Due date was: " + borrow.getDueDate();
    }
}