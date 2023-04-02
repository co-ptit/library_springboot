package co.ptit.api;

import co.ptit.domain.dto.request.UsersRequestDto;
import co.ptit.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody UsersRequestDto request) {
        return ResponseEntity.ok(usersService.create(request));
    }
}
