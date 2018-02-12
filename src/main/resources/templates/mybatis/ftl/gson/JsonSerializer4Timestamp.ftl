package ${packageName}.configuration.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ${packageName}.configuration.SystemConstant;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * Created by SongWei on 19/06/2017.
 */
public class JsonSerializer4Timestamp implements JsonSerializer<Timestamp> {
    @Override
    public JsonElement serialize(Timestamp src, Type typeOfSrc, JsonSerializationContext context) {
        if (Objects.isNull(src)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(SystemConstant.TIMESTAMP_FORMAT);
        return new JsonPrimitive(sdf.format(src));
    }
}
