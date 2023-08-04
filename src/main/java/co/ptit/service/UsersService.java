package co.ptit.service;

import co.ptit.domain.dto.request.LoginRequestDto;
import co.ptit.domain.dto.request.RegisterRequestDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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

    /**
     * Upload avatar
     *
     * @param data: image data
     * @return true or error
     */
    boolean updateAvatar(MultipartFile data, Long userId);

    void export(HttpServletResponse response);

    void downloadTemplate(HttpServletResponse response);

    @Transactional
    Boolean importUsers(MultipartFile file);

}
