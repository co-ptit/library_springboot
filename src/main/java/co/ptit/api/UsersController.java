package co.ptit.api;

import co.ptit.domain.dto.ResponseDto;
import co.ptit.domain.dto.request.LoginRequestDto;
import co.ptit.domain.dto.request.RegisterRequestDto;
import co.ptit.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * project: library_springboot
 * date:    4/2/2023
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/library/users")
public class UsersController {

    private final UsersService usersService;

    /**
     * Create Users
     *
     * @param request: RegisterRequestDto
     * @return true or error
     */
    @PostMapping("/register")
    ResponseDto<Object> register(@RequestBody RegisterRequestDto request) {
        return ResponseDto.ok(usersService.register(request));
    }

    /**
     * Login
     *
     * @param request: LoginRequestDto
     * @return data or error
     */
    @PostMapping("/login")
    ResponseDto<Object> login(@RequestBody LoginRequestDto request) {
        return ResponseDto.ok(usersService.login(request));
    }

}
