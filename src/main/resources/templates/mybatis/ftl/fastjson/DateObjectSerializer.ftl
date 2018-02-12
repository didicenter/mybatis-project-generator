package ${packageName}.configuration.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import ${packageName}.configuration.SystemConstant;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public abstract class DateObjectSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter writer = serializer.out;
        if (object == null) {
            writer.writeNull();
        } else {
            Date date = (Date) object;
            DateFormat format = new SimpleDateFormat(SystemConstant.DATE_FORMAT, Locale.CHINA);
            format.setTimeZone(TimeZone.getDefault());
            String text = format.format(date);
            writer.writeString(text);
            return;
        }
    }

}
