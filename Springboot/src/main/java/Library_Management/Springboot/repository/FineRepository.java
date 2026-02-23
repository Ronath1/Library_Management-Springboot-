package Library_Management.Springboot.repository;

import Library_Management.Springboot.entity.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FineRepository extends JpaRepository<Fine, Long> {
    List<Fine> findByMemberId(Long memberId);
    List<Fine> findByPaidFalse();
}