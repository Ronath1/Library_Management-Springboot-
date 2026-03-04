package Library_Management.Springboot.repository;

import Library_Management.Springboot.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
