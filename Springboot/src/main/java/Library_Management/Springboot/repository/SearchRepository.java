package Library_Management.Springboot.repository;

import Library_Management.Springboot.entity.Search;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<Search, Long> {
}

