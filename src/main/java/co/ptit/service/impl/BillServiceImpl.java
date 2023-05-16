package co.ptit.service.impl;

import co.ptit.domain.dto.request.BillRequestDto;
import co.ptit.repo.BillRepository;
import co.ptit.service.BillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * project: library_springboot
 * date:    5/16/2023
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    @Override
    public boolean create(BillRequestDto request) {
        return false;
    }
}
