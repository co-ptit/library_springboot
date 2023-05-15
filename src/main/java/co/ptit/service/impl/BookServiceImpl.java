package co.ptit.service.impl;

import co.ptit.domain.dto.request.BookRequestDto;
import co.ptit.domain.dto.response.BookResponseDto;
import co.ptit.domain.entity.Book;
import co.ptit.domain.entity.Category;
import co.ptit.exception.ValidateCommonException;
import co.ptit.repo.BookRepository;
import co.ptit.repo.CategoryRepository;
import co.ptit.service.BookService;
import co.ptit.utils.Constant;
import co.ptit.utils.InputValidateUtil;
import co.ptit.utils.MsgUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * project: library_springboot
 * date:    5/13/2023
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public boolean create(BookRequestDto request) {
        String title = request.getTitle();
        String author = request.getAuthor();
        Long categoryId = request.getCategoryId();
        LocalDate releaseDate = request.getReleaseDate();
        Long price = request.getPrice();
        Integer pageNumber = request.getPageNumber();
        Long quantitySold = request.getQuantitySold();
        String description = request.getDescription();

        //validate
        InputValidateUtil.validateNotNull("title", title);
        InputValidateUtil.validateNotNull("author", author);
        InputValidateUtil.validateNotNull("description", description);
        if (releaseDate.isBefore(LocalDate.now())) {
            throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "releaseDate"));
        }
        if (price <= 0) {
            throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "price"));
        }
        if (quantitySold <= 0) {
            throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "quantitySold"));
        }
        if (categoryId <= 0) {
            throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "categoryId"));
        }
        if (pageNumber <= 0) {
            throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "pageNumber"));
        }

        //check exists
        categoryRepository.findByCategoryIdAndStatus(categoryId, Constant.STATUS.ACTIVE.value())
                .orElseThrow(() -> {
                    throw new ValidateCommonException(
                            MsgUtil.getMessage("category.not.exists", "id", categoryId));
                });
        bookRepository.findByTitleIgnoreCaseAndAuthorIgnoreCaseAndStatus(title, author, Constant.STATUS.ACTIVE.value())
                .ifPresent(b -> {
                    throw new ValidateCommonException(
                            MsgUtil.getMessage("book.exists", "title", title, "author", author));
                });

        //create book
        Book book = Book.builder().build();
        BeanUtils.copyProperties(request, book);
        book.setCreateDatetime(LocalDateTime.now());
        book.setStatus(Constant.STATUS.ACTIVE.value());
        bookRepository.save(book);
        return Boolean.TRUE;
    }

    @Override
    public BookResponseDto read(Long id) {
        //validate
        if (id <= 0) {
            throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "id"));
        }

        Book book = bookRepository.findByBookIdAndStatus(id, Constant.STATUS.ACTIVE.value())
                .orElseThrow(() -> {
                    throw new ValidateCommonException(MsgUtil.getMessage("book.not.exists", "id", id));
                });
        BookResponseDto result = BookResponseDto.builder().build();
        BeanUtils.copyProperties(book, result);

        Category category = categoryRepository
                .findByCategoryIdAndStatus(book.getCategoryId(), Constant.STATUS.ACTIVE.value()).orElseThrow();
        result.setCategoryName(category.getName());
        return result;
    }

    @Override
    public boolean update(BookRequestDto request) {
        Long id = request.getBookId();
        Long categoryId = request.getCategoryId();
        LocalDate releaseDate = request.getReleaseDate();
        Long price = request.getPrice();
        Integer pageNumber = request.getPageNumber();
        Long quantitySold = request.getQuantitySold();
        String description = request.getDescription();

        //validate
        InputValidateUtil.validateNotNull("description", description);
        if (releaseDate.isBefore(LocalDate.now())) {
            throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "releaseDate"));
        }
        if (price <= 0) {
            throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "price"));
        }
        if (quantitySold <= 0) {
            throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "quantitySold"));
        }
        if (categoryId <= 0) {
            throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "categoryId"));
        }
        if (pageNumber <= 0) {
            throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "pageNumber"));
        }

        //check exists
        categoryRepository.findByCategoryIdAndStatus(categoryId, Constant.STATUS.ACTIVE.value())
                .orElseThrow(() -> {
                    throw new ValidateCommonException(
                            MsgUtil.getMessage("category.not.exists", "id", categoryId));
                });
        Book book = bookRepository.findByBookIdAndStatus(id, Constant.STATUS.ACTIVE.value())
                .orElseThrow(() -> {
                    throw new ValidateCommonException(MsgUtil.getMessage("book.not.exists", "id", id));
                });

        //create book
        request.setTitle(book.getTitle());
        request.setAuthor(book.getAuthor());
        BeanUtils.copyProperties(request, book);
        book.setUpdateDatetime(LocalDateTime.now());
        bookRepository.save(book);
        return Boolean.TRUE;
    }

    @Override
    public boolean delete(Long id) {
        //validate
        if (id <= 0) {
            throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "id"));
        }

        Book book = bookRepository.findByBookIdAndStatus(id, Constant.STATUS.ACTIVE.value())
                .orElseThrow(() -> {
                    throw new ValidateCommonException(MsgUtil.getMessage("book.not.exists", "id", id));
                });
        book.setStatus(Constant.STATUS.INACTIVE.value());
        bookRepository.save(book);
        return Boolean.TRUE;
    }
}
