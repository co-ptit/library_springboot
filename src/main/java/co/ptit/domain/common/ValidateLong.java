package co.ptit.domain.common;

import co.ptit.exception.ValidateCommonException;
import co.ptit.utils.MsgUtil;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

/**
 * @author: HieuDo
 * @since: 7/30/2024
 * @project: library_springboot
 */

public class ValidateLong extends JsonDeserializer {

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        try {
            if (!ObjectUtils.isEmpty(jsonParser.getText()))
                return Long.parseLong(jsonParser.getText());
            return null;
        } catch (Exception e) {
            throw new ValidateCommonException(MsgUtil
                    .getMessage("long.number.invalid.format", jsonParser.getCurrentName(), jsonParser.getText()));
        }
    }
}
