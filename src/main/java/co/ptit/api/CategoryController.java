package co.ptit.api;

import co.ptit.domain.dto.ResponseDto;
import co.ptit.domain.dto.request.BookRequestDto;
import co.ptit.domain.dto.request.CategoryRequestDto;
import co.ptit.service.CategoryService;
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
@RequestMapping("/api/library/category")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Create Category
     *
     * @param request: BookRequestDto
     * @return true or error
     */
    @PostMapping("/create")
    ResponseDto<Object> create(@RequestBody CategoryRequestDto request) {
        return ResponseDto.ok("");
    }

    /**
     * Read Category
     *
     * @param id:
     * @return BookResponseDto
     */
    @GetMapping("/read")
    ResponseDto<Object> read(@RequestParam("id") Long id) {
        return ResponseDto.ok("");
    }

    /**
     * Update Category
     *
     * @param request: BookRequestDto
     * @return true or error
     */
    @PutMapping("/update")
    ResponseDto<Object> update(@RequestBody BookRequestDto request) {
        return ResponseDto.ok("");
    }

    /**
     * Delete Category
     *
     * @param id:
     * @return true or error
     */
    @DeleteMapping("/delete")
    ResponseDto<Object> delete(@RequestParam("id") Long id) {
        return ResponseDto.ok("");
    }

}
