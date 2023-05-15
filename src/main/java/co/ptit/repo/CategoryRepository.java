package co.ptit.repo;

import co.ptit.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * project: library_springboot
 * date:    5/13/2023
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCodeAndStatus(String code, Integer status);

    Optional<Category> findByCategoryIdAndStatus(Long id, Integer status);

}
