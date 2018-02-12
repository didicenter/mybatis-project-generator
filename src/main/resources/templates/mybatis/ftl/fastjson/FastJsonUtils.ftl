package ${packageName}.configuration.fastjson;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;

import java.math.BigDecimal;
import java.sql.Timestamp;


public class FastJsonUtils {

    public static FastJsonConfig defaultConfig() {

        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.DisableCircularReferenceDetect
        );

        SerializeConfig config = new SerializeConfig();
        config.put(Timestamp.class, new TimestampObjectSerializer());
        config.put(java.util.Date.class, new UtilDateObjectSerializer());
        config.put(java.sql.Date.class, new SqlDateObjectSerializer());
        config.put(Float.class, new FloatObjectSerializer());
        config.put(Double.class, new DoubleObjectSerializer());
        config.put(BigDecimal.class, new BigDecimalObjectSerializer());

        fastJsonConfig.setSerializeConfig(config);

        return fastJsonConfig;
    }


}
