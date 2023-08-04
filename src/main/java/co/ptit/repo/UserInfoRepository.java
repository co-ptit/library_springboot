package co.ptit.repo;

import co.ptit.domain.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * project: library_springboot
 * date:    4/17/2023
 */

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByPhoneNumberAndStatus(String phoneNumber, Integer status);

    Optional<UserInfo> findByEmailAndStatus(String email, Integer status);

    Optional<UserInfo> findByUserInfoIdAndStatus(Long userInfoId, Integer status);

    @Query("select u.phoneNumber from UserInfo u where u.status = 1")
    Set<String> findAllPhoneNumber();

    @Query("select u.email from UserInfo u where u.status = 1")
    Set<String> findAllEmail();
}
