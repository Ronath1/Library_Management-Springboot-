package Library_Management.Springboot.controller;


import Library_Management.Springboot.entity.Fine;
import Library_Management.Springboot.repository.FineRepository;
import Library_Management.Springboot.service.FineService;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fines")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class FineController {
    private final FineService fineService;
    private final FineRepository fineRepository;

    @GetMapping
    public List<Fine> getAllFines() {
        return fineRepository.findAll();
    }

    @GetMapping("/unpaid")
    public List<Fine> getUnpaidFines() {
        return fineRepository.findByPaidFalse();
    }

    @PutMapping("/{id}/pay")
    public String payFine(@PathVariable Long id) {
        fineService.markAsPaid(id);
        return "Fine paid successfully!";
    }
}