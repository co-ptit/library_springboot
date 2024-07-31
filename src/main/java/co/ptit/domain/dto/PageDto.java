package co.ptit.domain.dto;

import co.ptit.utils.Constant;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author: HieuDo
 * @since: 7/30/2024
 * @project: library_springboot
 */

@Getter
@Setter
public class PageDto<T> implements Serializable {

    private PageableDTO page = new PageableDTO();

    private List<T> data;

    public PageDto() {
    }

    public PageDto(List<T> data, int pageIndex, int pageSize, long total) {
        this.data = data;
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setTotal(total);
    }

    public <U> PageDto(Page<U> pageInput, Function<List<U>, List<T>> mapper) {
        Pageable pageable = pageInput.getPageable();
        page.setPageIndex(pageable.getPageNumber());
        page.setPageSize(pageable.getPageSize());
        page.setTotal(pageInput.getTotalElements());
        List<T> content = mapper.apply(pageInput.getContent());
        if (content != null) {
            this.data = content;
        }
    }

    public static <T> PageDto<T> of(List<T> data, int pageIndex, int pageSize, long total) {
        return new PageDto<>(data, pageIndex, pageSize, total);
    }

    public static <T> PageDto<T> empty() {
        return new PageDto<>(new ArrayList<>(), Constant.PAGE_INDEX_DEFAULT, Constant.PAGE_SIZE_DEFAULT, 0);
    }

    @Data
    public static class PageableDTO implements Serializable {
        private int pageIndex = 0;
        private int pageSize = 0;
        private long total = 0;
    }
}