package co.ptit.repo;

import co.ptit.domain.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * project: library_springboot
 * date:    4/17/2023
 */

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByPhoneNumberAndStatus(String phoneNumber, Integer status);

    Optional<UserInfo> findByEmailAndStatus(String email, Integer status);
}
