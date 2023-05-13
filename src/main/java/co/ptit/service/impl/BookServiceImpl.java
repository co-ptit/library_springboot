package co.ptit.service.impl;

import co.ptit.domain.dto.request.BookRequestDto;
import co.ptit.domain.dto.response.BookResponseDto;
import co.ptit.repo.BookRepository;
import co.ptit.service.BookService;
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
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public boolean create(BookRequestDto request) {

        return false;
    }

    @Override
    public BookResponseDto read(Long id) {
        return null;
    }

    @Override
    public boolean update(BookRequestDto request) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
