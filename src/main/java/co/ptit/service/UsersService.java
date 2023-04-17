package co.ptit.service;

import co.ptit.domain.dto.request.LoginRequestDto;
import co.ptit.domain.dto.request.RegisterRequestDto;
import org.springframework.transaction.annotation.Transactional;

/**
 * project: library_springboot
 * date:    4/2/2023
 */

public interface UsersService {

    /**
     * Create Users
     *
     * @param request: RegisterRequestDto
     * @return true or error
     */
    @Transactional
    boolean register(RegisterRequestDto request);

    /**
     * Login
     *
     * @param request: LoginRequestDto
     * @return data or error
     */
    Object login(LoginRequestDto request);
}
