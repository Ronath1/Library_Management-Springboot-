package Library_Management.Springboot.controller;

import Library_Management.Springboot.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public Map<String, Object> getGeneralReport() {

        return reportService.getGeneralReport();
    }
}
