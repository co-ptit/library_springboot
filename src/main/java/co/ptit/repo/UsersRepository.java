package co.ptit.repo;

import co.ptit.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * project: library_springboot
 * date:    4/2/2023
 */

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUserNameAndStatus(String userName, Integer status);

    Optional<Users> findByUserIdAndStatus(Long userId, Integer status);

    List<Users> findAllByStatus(Integer status);

}
