package co.ptit.service;

import co.ptit.domain.dto.request.CategoryRequestDto;
import co.ptit.domain.dto.response.CategoryResponseDto;

/**
 * project: library_springboot
 * date:    5/13/2023
 */

public interface CategoryService {

    /**
     * Create Category
     *
     * @param request: CategoryRequestDto
     * @return true or error
     */
    boolean create(CategoryRequestDto request);

    /**
     * Read Category
     *
     * @param id:
     * @return CategoryResponseDto
     */
    CategoryResponseDto read(Long id);

    /**
     * Update Category
     *
     * @param request: BookRequestDto
     * @return true or error
     */
    boolean update(CategoryRequestDto request);

    /**
     * Delete Category
     *
     * @param id:
     * @return true or error
     */
    boolean delete(Long id);

}
