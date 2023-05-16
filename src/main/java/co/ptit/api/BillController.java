package co.ptit.api;

import co.ptit.domain.dto.ResponseDto;
import co.ptit.domain.dto.request.BillRequestDto;
import co.ptit.service.BillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * project: library_springboot
 * date:    5/16/2023
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/library/bill")
public class BillController {

    private final BillService billService;

    /**
     * Create Bill
     *
     * @param request: BillRequestDto
     * @return true or error
     */
    @PostMapping("/create")
    ResponseDto<Object> create(@RequestBody BillRequestDto request) {
        return ResponseDto.ok(billService.create(request));
    }
}
