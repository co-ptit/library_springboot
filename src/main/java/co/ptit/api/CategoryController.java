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
        return ResponseDto.ok(categoryService.create(request));
    }

    /**
     * Read Category
     *
     * @param code:
     * @return BookResponseDto
     */
    @GetMapping("/read")
    ResponseDto<Object> read(@RequestParam("code") String code) {
        return ResponseDto.ok(categoryService.read(code));
    }

    /**
     * Update Category
     *
     * @param request: BookRequestDto
     * @return true or error
     */
    @PutMapping("/update")
    ResponseDto<Object> update(@RequestBody CategoryRequestDto request) {
        return ResponseDto.ok(categoryService.update(request));
    }

    /**
     * Delete Category
     *
     * @param code:
     * @return true or error
     */
    @DeleteMapping("/delete")
    ResponseDto<Object> delete(@RequestParam("code") String code) {
        return ResponseDto.ok(categoryService.delete(code));
    }

}
