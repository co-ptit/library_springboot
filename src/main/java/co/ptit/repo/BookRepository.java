package co.ptit.repo;

import co.ptit.domain.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * project: library_springboot
 * date:    5/13/2023
 */

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitleIgnoreCaseAndAuthorIgnoreCaseAndStatus(String title, String author, Integer status);

    Optional<Book> findByBookIdAndStatus(Long id, Integer status);

    Page<Book> findAllByStatus(Integer status, Pageable pageable);
}
