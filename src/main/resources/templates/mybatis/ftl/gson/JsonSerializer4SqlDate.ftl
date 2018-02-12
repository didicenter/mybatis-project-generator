package ${packageName}.configuration.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ${packageName}.configuration.SystemConstant;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Objects;


public class JsonSerializer4SqlDate extends JsonSerializer4Date {
//    @Override
//    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
//        if (Objects.isNull(src)) {
//            return null;
//        }
//        SimpleDateFormat sdf = new SimpleDateFormat(SystemConstant.DATE_FORMAT);
//        return new JsonPrimitive(sdf.format(src));
//    }
}
