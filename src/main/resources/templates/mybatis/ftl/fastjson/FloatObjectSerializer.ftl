package ${packageName}.configuration.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import ${packageName}.configuration.SystemConstant;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;


public class FloatObjectSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter writer = serializer.out;
        if (object == null) {
            writer.writeNull();
        } else {
            Float data = (Float) object;
            BigDecimal decimal = new BigDecimal(data);
            decimal = decimal.setScale(SystemConstant.NUMBER_SHOW_FORMAT, BigDecimal.ROUND_HALF_UP);
            String result = decimal.toString();
            writer.write(result);
            return;
        }
    }

}
