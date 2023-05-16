package co.ptit.service;

import co.ptit.domain.dto.request.BillRequestDto;

/**
 * project: library_springboot
 * date:    5/16/2023
 */

public interface BillService {

    /**
     * Create Bill
     *
     * @param request: BillRequestDto
     * @return true or error
     */
    boolean create(BillRequestDto request);
}
