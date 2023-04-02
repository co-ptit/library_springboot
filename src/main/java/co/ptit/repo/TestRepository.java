package co.ptit.repo;

import co.ptit.domain.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * project: library_springboot
 * date:    4/2/2023
 */

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

    Optional<Test> findByIdAndStatus(Long id, int status);
}
