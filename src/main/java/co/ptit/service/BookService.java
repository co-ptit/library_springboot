package co.ptit.service;

import co.ptit.domain.dto.request.BookRequestDto;
import co.ptit.domain.dto.response.BookResponseDto;

/**
 * project: library_springboot
 * date:    5/13/2023
 */

public interface BookService {

    /**
     * Create Book
     *
     * @param request: BookRequestDto
     * @return true or error
     */
    boolean create(BookRequestDto request);

    /**
     * Read Book
     *
     * @param id:
     * @return BookResponseDto
     */
    BookResponseDto read(Long id);

    /**
     * Update Book
     *
     * @param request: BookRequestDto
     * @return true or error
     */
    boolean update(BookRequestDto request);

    /**
     * Delete Book
     *
     * @param id:
     * @return true or error
     */
    boolean delete(Long id);

}
