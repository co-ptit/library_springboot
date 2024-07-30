package co.ptit.domain.common;

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

public class StripString extends JsonDeserializer {

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return ObjectUtils.isEmpty(jsonParser.getText()) ? null : jsonParser.getText().strip();
    }
}
