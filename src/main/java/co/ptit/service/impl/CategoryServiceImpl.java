package co.ptit.service.impl;

import co.ptit.domain.dto.request.CategoryRequestDto;
import co.ptit.domain.dto.response.CategoryResponseDto;
import co.ptit.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * project: library_springboot
 * date:    5/13/2023
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Override
    public boolean create(CategoryRequestDto request) {
        return false;
    }

    @Override
    public CategoryResponseDto read(Long id) {
        return null;
    }

    @Override
    public boolean update(CategoryRequestDto request) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
