package co.ptit.repo;

import co.ptit.domain.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * project: library_springboot
 * date:    5/16/2023
 */

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
}
