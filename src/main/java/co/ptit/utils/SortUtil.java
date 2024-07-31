package co.ptit.utils;

import co.ptit.domain.dto.request.common.OrderRequest;
import co.ptit.exception.ValidateCommonException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.ObjectUtils;

import javax.naming.ldap.PagedResultsControl;
import java.security.cert.CertPathValidatorException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: HieuDo
 * @since: 7/30/2024
 * @project: library_springboot
 */

public class SortUtil {

    public static final class PARAM_SORT {

        public static final String ASC = "ASC";
        public static final String DESC = "DESC";
        public static final String CODE = "code";
        public static final String NAME = "name";
        public static final String CREATE_DATE_TIME = "createDatetime";
        public static final String EFFECTIVE_DATE = "effectiveDate";
        public static final String EXPIRATION_DATE = "expirationDate";

        public static final List<String> ORDER_BY = List.of(ASC, DESC);

        public static final List<String> TEST_KEY_SORT = List.of(CODE, NAME);

    }

    public static Pageable getPageable(Integer pageIndex, Integer pageSize, List<OrderRequest> orderList, List<String> keySort){
        return PageRequest.of(pageIndex, pageSize, getSort(orderList, keySort));
    }

    private static Sort getSort(List<OrderRequest> orderList, List<String> keySort) {
        List<Sort.Order> result = new ArrayList<>();
        if (ObjectUtils.isEmpty(orderList))
            return Sort.by(Sort.Order.desc(PARAM_SORT.CREATE_DATE_TIME));
        for (OrderRequest order : orderList) {
            if (ObjectUtils.isEmpty(order))
                throw new ValidateCommonException(MsgUtil.getMessage("key.sort.required"));
            if (ObjectUtils.isEmpty(order.getOrderBy()))
                order.setOrderBy(PARAM_SORT.ASC);

            if (!keySort.contains(order.getKeySort()) || !PARAM_SORT.ORDER_BY.contains(order.getOrderBy().toUpperCase()))
                return Sort.by(Sort.Order.desc(PARAM_SORT.CREATE_DATE_TIME));
            else if (PARAM_SORT.ASC.equalsIgnoreCase(order.getOrderBy()))
                result.add(Sort.Order.asc(order.getKeySort()));
            else
                result.add(Sort.Order.desc(order.getKeySort()));
        }
        return Sort.by(result);
    }
}
