package co.ptit.domain.common;

import co.ptit.exception.DateException;
import co.ptit.utils.Constant;
import co.ptit.utils.DateUtil;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: HieuDo
 * @since: 7/8/2024
 * @project: library_springboot
 */

public class ValidateDate extends JsonDeserializer {

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String value = jsonParser.getText().strip();
        if (ObjectUtils.isEmpty(value))
            return null;

        LocalDate localDate = DateUtil.parseToLocalDate(value);
        int day = Integer.parseInt(value.substring(Constant.START_DAY, Constant.END_DAY));
        int month = Integer.parseInt(value.substring(Constant.START_MONTH, Constant.END_MONTH));
        int year = Integer.parseInt(value.substring(Constant.END_MONTH + 1));
        if (day > Constant.MAX_DAY)
            throw new DateException(value);

        List<Integer> monthList = Stream.of(Constant.MONTH_EVEN.values())
                .map(Constant.MONTH_EVEN::value)
                .collect(Collectors.toList());
        if (monthList.contains(month) && day == Constant.MAX_DAY)
            throw new DateException(value);
        else if (month == 2){
            // năm nhuận 29 ngày
            if (day > Constant.MAX_DAY_FEB)
                throw new DateException(value);

            // còn lại 29 ngày
            if (!(year % 4 == 0) && day >= Constant.MAX_DAY_FEB)
                throw new DateException(value);
        }
        return localDate;
    }
}
