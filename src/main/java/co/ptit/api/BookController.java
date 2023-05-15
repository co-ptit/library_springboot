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
        return ResponseDto.ok(bookService.create(request));
    }

    /**
     * Read Book
     *
     * @param id:
     * @return BookResponseDto
     */
    @GetMapping("/read")
    ResponseDto<Object> read(@RequestParam("id") Long id) {
        return ResponseDto.ok(bookService.read(id));
    }

    /**
     * Update Book
     *
     * @param request: BookRequestDto
     * @return true or error
     */
    @PutMapping("/update")
    ResponseDto<Object> update(@RequestBody BookRequestDto request) {
        return ResponseDto.ok(bookService.update(request));
    }

    /**
     * Delete Book
     *
     * @param id:
     * @return true or error
     */
    @DeleteMapping("/delete")
    ResponseDto<Object> delete(@RequestParam("id") Long id) {
        return ResponseDto.ok(bookService.delete(id));
    }

    /**
     * Search Book
     *
     * @param page, size:
     * @return List<BookResponseDto>
     */
    @GetMapping("/search")
    ResponseDto<Object> search(@RequestParam int page, @RequestParam int size) {
        return ResponseDto.ok(bookService.search(page, size));
    }

}
