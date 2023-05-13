package co.ptit.api;

import co.ptit.domain.dto.ResponseDto;
import co.ptit.domain.dto.request.BookRequestDto;
import co.ptit.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * project: library_springboot
 * date:    5/13/2023
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/library/book")
public class BookController {

    private final BookService bookService;

    /**
     * Create Book
     *
     * @param request: BookRequestDto
     * @return true or error
     */
    @PostMapping("/create")
    ResponseDto<Object> create(@RequestBody BookRequestDto request) {
        return ResponseDto.ok("");
    }

    /**
     * Read Book
     *
     * @param id:
     * @return BookResponseDto
     */
    @PostMapping("/read")
    ResponseDto<Object> read(@RequestParam("id") Long id) {
        return ResponseDto.ok("");
    }

    /**
     * Update Book
     *
     * @param request: BookRequestDto
     * @return true or error
     */
    @PostMapping("/update")
    ResponseDto<Object> update(@RequestBody BookRequestDto request) {
        return ResponseDto.ok("");
    }

    /**
     * Delete Book
     *
     * @param id:
     * @return true or error
     */
    @PostMapping("/delete")
    ResponseDto<Object> delete(@RequestParam("id") Long id) {
        return ResponseDto.ok("");
    }

}
