package ${packageName}.configuration.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ${packageName}.configuration.SystemConstant;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Objects;


public class JsonSerializer4Double implements JsonSerializer<Double> {
    @Override
    public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
        if (Objects.isNull(src)) {
            src = 0.00D;
        }
        if (src.isNaN()) {
            src = 0.00D;
        }
        return new JsonPrimitive(new BigDecimal(src).setScale(SystemConstant.NUMBER_SHOW_FORMAT, BigDecimal.ROUND_HALF_UP));
    }
}
