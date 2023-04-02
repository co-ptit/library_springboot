package co.ptit.service.impl;

import co.ptit.domain.dto.request.UsersRequestDto;
import co.ptit.repo.UsersRepository;
import co.ptit.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * project: library_springboot
 * date:    4/2/2023
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    public boolean create(UsersRequestDto request) {
        //TODO:
        return Boolean.TRUE;
    }
}
