package co.ptit.api;

import co.ptit.domain.dto.ResponseDto;
import co.ptit.domain.dto.request.DateTimeRequestDto;
import co.ptit.domain.dto.request.TestRequestDto;
import co.ptit.domain.entity.Test;
import co.ptit.repo.TestRepository;
import co.ptit.utils.Constant;
import co.ptit.utils.MsgUtil;
import com.tinify.Tinify;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;

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
    ResponseDto<?> create(@RequestBody TestRequestDto request) {
        testRepository.save(Test.builder()
                .code(request.getCode())
                .name(request.getName())
                .status(Constant.STATUS.ACTIVE.value())
                .createUser(request.getUserName())
                .createDatetime(LocalDateTime.now())
                .build());
        return ResponseDto.ok(Boolean.TRUE);
    }

    @GetMapping("/read")
    ResponseDto<?> read(@RequestParam("id") Long id) {
        return ResponseDto.ok(testRepository.findByIdAndStatus(id, Constant.STATUS.ACTIVE.value()));
    }

    @PutMapping("/update")
    ResponseDto<?> update(@RequestBody TestRequestDto request) {
        testRepository.save(Test.builder()
                .id(request.getId())
                .code(request.getCode())
                .name(request.getName())
                .status(Constant.STATUS.ACTIVE.value())
                .updateDatetime(LocalDateTime.now())
                .build());
        return ResponseDto.ok(Boolean.TRUE);
    }

    @DeleteMapping("/delete")
    ResponseDto<?> delete(@RequestParam("id") Long id) {
        testRepository.save(Test.builder()
                .id(id)
                .status(Constant.STATUS.INACTIVE.value())
                .updateDatetime(LocalDateTime.now())
                .build());
        return ResponseDto.ok(Boolean.TRUE);
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

    @GetMapping("/compression-image")
    ResponseDto<Object> compressionImage(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] sourceData = file.getBytes();
        byte[] resultData;
        if (sourceData.length > Constant.MAX_SIZE_ECM * 1024) {
            resultData = Tinify.fromBuffer(sourceData).toBuffer();
        } else {
            resultData = sourceData;
        }
        return ResponseDto.ok("source: " + sourceData.length + "; result: " + resultData.length);
    }

    @GetMapping("/compression-image-v2")
    ResponseDto<Object> compressionImageV2(@RequestParam("file") MultipartFile file) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Thumbnails.of(new ByteArrayInputStream(file.getBytes()))
                    .size(450, 600)
                    .outputFormat("png")
                    .outputQuality(0.5)
                    .toOutputStream(outputStream);


            return ResponseDto.ok(Base64.getEncoder().encodeToString(outputStream.toByteArray()));
        } catch (Exception e) {
            log.error("err: {}", e.getMessage());
        }
        return ResponseDto.ok("");
    }

    @PostMapping("/date-time")
    ResponseDto<Object> dateTime(@RequestBody DateTimeRequestDto request) {
        return ResponseDto.ok(request);
    }

}
