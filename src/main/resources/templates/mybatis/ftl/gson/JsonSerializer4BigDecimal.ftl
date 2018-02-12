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
public class JsonSerializer4BigDecimal implements JsonSerializer<BigDecimal> {
    @Override
    public JsonElement serialize(BigDecimal src, Type typeOfSrc, JsonSerializationContext context) {
        if (Objects.isNull(src)) {
            src = new BigDecimal(0.00);
        }
        return new JsonPrimitive(src.setScale(SystemConstant.NUMBER_SHOW_FORMAT, BigDecimal.ROUND_HALF_UP));
    }
}
