package co.ptit.repo;

import co.ptit.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * project: library_springboot
 * date:    4/2/2023
 */

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}
