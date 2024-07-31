package co.ptit.service;

import co.ptit.domain.dto.PageDto;
import co.ptit.domain.dto.request.TestSearchRequestDto;
import co.ptit.domain.dto.response.TestResponseDto;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: HieuDo
 * @since: 7/30/2024
 * @project: library_springboot
 */

@Transactional
public interface TestService {

    Boolean create(Integer number);

    PageDto<TestResponseDto> search(TestSearchRequestDto request);

}
