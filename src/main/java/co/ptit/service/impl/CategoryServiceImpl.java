package co.ptit.service.impl;

import co.ptit.domain.dto.request.CategoryRequestDto;
import co.ptit.domain.dto.response.CategoryResponseDto;
import co.ptit.domain.entity.Category;
import co.ptit.exception.ValidateCommonException;
import co.ptit.repo.CategoryRepository;
import co.ptit.service.CategoryService;
import co.ptit.utils.Constant;
import co.ptit.utils.InputValidateUtil;
import co.ptit.utils.MsgUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * project: library_springboot
 * date:    5/13/2023
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public boolean create(CategoryRequestDto request) {
        String code = request.getCode();
        String name = request.getName();
        String description = request.getDescription();

        //validate
        InputValidateUtil.validateNotNull("code", code);
        InputValidateUtil.validateNotNull("name", name);
        InputValidateUtil.validateNotNull("description", description);

        //check exists
        categoryRepository.findByCodeAndStatus(code, Constant.STATUS.ACTIVE.value())
                .ifPresent(c -> {
                    throw new ValidateCommonException(MsgUtil.getMessage("category.exists", "code", code));
                });

        //create Category
        Category category = Category.builder()
                .code(code)
                .name(name)
                .description(description)
                .status(Constant.STATUS.ACTIVE.value())
                .createDatetime(LocalDateTime.now())
                .build();
        categoryRepository.save(category);
        return Boolean.TRUE;
    }

    @Override
    public CategoryResponseDto read(String code) {
        //validate
        InputValidateUtil.validateNotNull("code", code);

        Category category = categoryRepository.findByCodeAndStatus(code, Constant.STATUS.ACTIVE.value())
                .orElseThrow(() -> {
                    throw new ValidateCommonException(MsgUtil.getMessage("category.not.exists", "code", code));
                });

        CategoryResponseDto result = CategoryResponseDto.builder().build();
        BeanUtils.copyProperties(category, result);
        return result;
    }

    @Override
    public boolean update(CategoryRequestDto request) {
        String code = request.getCode();
        String name = request.getName();
        String description = request.getDescription();

        //validate
        InputValidateUtil.validateNotNull("code", code);
        InputValidateUtil.validateNotNull("name", name);
        InputValidateUtil.validateNotNull("description", description);

        //update Category
        Category category = categoryRepository.findByCodeAndStatus(code, Constant.STATUS.ACTIVE.value())
                .orElseThrow(() -> {
                    throw new ValidateCommonException(MsgUtil.getMessage("category.not.exists", "code", code));
                });
        category.setName(name);
        category.setDescription(description);
        category.setUpdateDatetime(LocalDateTime.now());
        categoryRepository.save(category);
        return Boolean.TRUE;
    }

    @Override
    public boolean delete(String code) {
        //validate
        InputValidateUtil.validateNotNull("code", code);

        //delete Category
        Category category = categoryRepository.findByCodeAndStatus(code, Constant.STATUS.ACTIVE.value())
                .orElseThrow(() -> {
                    throw new ValidateCommonException(MsgUtil.getMessage("category.not.exists", "code", code));
                });
        category.setStatus(Constant.STATUS.INACTIVE.value());
        category.setUpdateDatetime(LocalDateTime.now());
        categoryRepository.save(category);
        return Boolean.TRUE;
    }
}
