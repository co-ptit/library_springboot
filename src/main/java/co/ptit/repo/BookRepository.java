package co.ptit.repo;

import co.ptit.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * project: library_springboot
 * author:  HieuDM
 * date:    5/13/2023
 */

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}