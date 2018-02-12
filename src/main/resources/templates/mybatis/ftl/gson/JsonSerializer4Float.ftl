package ${packageName}.configuration.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ${packageName}.configuration.SystemConstant;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by SongWei on 19/06/2017.
 */
public class JsonSerializer4Float implements JsonSerializer<Float> {
    @Override
    public JsonElement serialize(Float src, Type typeOfSrc, JsonSerializationContext context) {
        if (Objects.isNull(src)) {
            src = 0.00F;
        }
        if (src.isNaN()) {
            src = 0.00F;
        }
        return new JsonPrimitive(new BigDecimal(src).setScale(SystemConstant.NUMBER_SHOW_FORMAT, BigDecimal.ROUND_HALF_UP));
    }
}
