package Library_Management.Springboot.service;

import Library_Management.Springboot.repository.FineRepository;
import com.library.management.entity.Borrow;
import com.library.management.entity.Fine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FineService {
    private final FineRepository fineRepository;
    private final double DAILY_RATE = 2.0; // Set your fine per day (e.g., $2.00)
    public void calculateAndSaveFine(Borrow borrow) {
        if (borrow.getReturnDate().isAfter(borrow.getDueDate())) {
            long daysLate = ChronoUnit.DAYS.between(borrow.getDueDate(), borrow.getReturnDate());

            Fine fine = new Fine();
            fine.setAmount(daysLate * DAILY_RATE);
            fine.setFineDate(LocalDate.now());
            fine.setMember(borrow.getMember());
            fine.setBorrow(borrow);
            fine.setPaid(false);

            fineRepository.save(fine);
            System.out.println("Fine generated for Member: " + borrow.getMember().getName());
        }
    }

    public void markAsPaid(Long fineId) {
        Fine fine = fineRepository.findById(fineId)
        .orElseThrow(() -> new RuntimeException("Fine not found"));
        fine.setPaid(true);
        fineRepository.save(fine);
    }
}
