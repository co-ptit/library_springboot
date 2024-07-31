package co.ptit.repo;

import co.ptit.domain.dto.response.TestResponseDto;
import co.ptit.domain.entity.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * project: library_springboot
 * date:    4/2/2023
 */

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

    Optional<Test> findByIdAndStatus(Long id, int status);

    @Query("SELECT new co.ptit.domain.dto.response.TestResponseDto(t.id, t.code, t.name, t.status, t.createUser, " +
            "                                                    t.createDatetime, t.updateUser, t.updateDatetime)" +
            " FROM Test t" +
            " WHERE (lower(t.name) LIKE lower(:name) OR :name IS NULL)" +
            " AND (lower(t.code) LIKE lower(:code) OR :code IS NULL)" +
//            " AND (lower(t.code) LIKE lower(:code) escape '\\' OR :name IS NULL)" +
            " AND t.status = :status")
    Page<TestResponseDto> search(@Param("name") String name,
                                 @Param("code") String code,
                                 @Param("status") Integer status,
                                 Pageable pageable);
}
