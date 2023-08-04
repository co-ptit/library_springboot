package co.ptit.api;

import co.ptit.domain.dto.ResponseDto;
import co.ptit.domain.dto.request.LoginRequestDto;
import co.ptit.domain.dto.request.RegisterRequestDto;
import co.ptit.service.UsersService;
import co.ptit.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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


    /**
     * Upload avatar
     *
     * @param data: image data
     * @return true or error
     */
    @PostMapping("/update-avatar")
    ResponseDto<Object> updateAvatar(@RequestParam("data") MultipartFile data,
                                     @RequestParam("userId") Long userId) {
        return ResponseDto.ok(usersService.updateAvatar(data, userId));
    }

    /**
     * View avatar
     *
     * @param url: url in database
     * @return true or error
     */
    @ResponseBody
    @GetMapping(value = "/view-avatar", produces = MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<?> viewAvatar(@RequestParam("url") String url) {
        return ResponseEntity.ok().body(FileUtil.download(url));
    }

    @GetMapping("/export")
    void export(HttpServletResponse response) {
        usersService.export(response);
    }

    @GetMapping("/download-template")
    void downloadTemplate(HttpServletResponse response) {

    }

    @PostMapping("/import")
    void importUsers(@RequestBody MultipartFile file) {

    }

}
