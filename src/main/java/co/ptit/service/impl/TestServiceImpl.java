package co.ptit.service.impl;

import co.ptit.domain.dto.PageDto;
import co.ptit.domain.dto.request.TestSearchRequestDto;
import co.ptit.domain.dto.response.TestResponseDto;
import co.ptit.domain.entity.Test;
import co.ptit.repo.TestRepository;
import co.ptit.service.TestService;
import co.ptit.utils.Constant;
import co.ptit.utils.SortUtil;
import co.ptit.utils.SqlUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author: HieuDo
 * @since: 7/30/2024
 * @project: library_springboot
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    @Override
    public Boolean create(Integer number) {
        for (int i = 0; i < number; i++) {
            testRepository.save(Test.builder()
                    .code("code " + (int) (Math.random() * Integer.MAX_VALUE))
                    .name("name " + (int) (Math.random() * Integer.MAX_VALUE))
                    .status(Constant.STATUS.ACTIVE.value())
                    .createUser("SYSTEM")
                    .createDatetime(LocalDateTime.now())
                    .build());
        }
        return Boolean.TRUE;
    }

    @Override
    public PageDto<TestResponseDto> search(TestSearchRequestDto request) {
        Page<TestResponseDto> result = testRepository
                .search(SqlUtils.encodeAndReplaceKeyword(request.getName()),
                        SqlUtils.encodeAndReplaceKeyword(request.getCode()),
                        Constant.STATUS.ACTIVE.value(),
                        SortUtil.getPageable(request.getPageIndex(), request.getPageSize(), request.getOrderList(),
                                SortUtil.PARAM_SORT.TEST_KEY_SORT));
        return new PageDto<>(result.getContent(), request.getPageIndex(), request.getPageSize(), result.getTotalElements());
    }

}
