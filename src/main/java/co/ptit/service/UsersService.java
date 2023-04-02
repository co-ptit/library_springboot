package co.ptit.service;

import co.ptit.domain.dto.request.UsersRequestDto;

/**
 * project: library_springboot
 * date:    4/2/2023
 */

public interface UsersService {

    boolean create(UsersRequestDto request);
}
