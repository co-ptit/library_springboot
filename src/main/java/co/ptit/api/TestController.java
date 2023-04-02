package co.ptit.api;

import co.ptit.domain.dto.ResponseDto;
import co.ptit.domain.dto.request.TestRequestDto;
import co.ptit.domain.entity.Test;
import co.ptit.repo.TestRepository;
import co.ptit.utils.Constant;
import co.ptit.utils.MsgUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * project: library_springboot
 * date:    4/2/2023
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/library/test")
public class TestController {

    private final TestRepository testRepository;

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody TestRequestDto request) {
        testRepository.save(Test.builder()
                .code(request.getCode())
                .name(request.getName())
                .status(Constant.STATUS.ACTIVE.value())
                .createUser(request.getUserName())
                .createDatetime(LocalDateTime.now())
                .build());
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @GetMapping("/read")
    ResponseEntity<?> read(@RequestParam("id") Long id) {
        return ResponseEntity.ok(testRepository.findByIdAndStatus(id, Constant.STATUS.ACTIVE.value()));
    }

    @PutMapping("/update")
    ResponseEntity<?> update(@RequestBody TestRequestDto request) {
        testRepository.save(Test.builder()
                .id(request.getId())
                .code(request.getCode())
                .name(request.getName())
                .status(Constant.STATUS.ACTIVE.value())
                .updateDatetime(LocalDateTime.now())
                .build());
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @DeleteMapping("/delete")
    ResponseEntity<?> delete(@RequestParam("id") Long id) {
        testRepository.save(Test.builder()
                .id(id)
                .status(Constant.STATUS.INACTIVE.value())
                .updateDatetime(LocalDateTime.now())
                .build());
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @GetMapping("/success")
    ResponseDto<?> success(@RequestParam("id") Long id) {
        return ResponseDto.ok(testRepository.findByIdAndStatus(id, Constant.STATUS.ACTIVE.value()));
    }

    @GetMapping("/err")
    ResponseDto<?> err() {
        return ResponseDto.err("err", "Message error");
    }

    @GetMapping("/error")
    ResponseDto<?> error() {
        throw new IllegalArgumentException(MsgUtil.getMessage("test.01"));
    }
}
