package ${packageName}.configuration.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by SongWei on 09/02/2018.
 */
public class GsonJsonUtils {

    public static Gson defaultGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.setPrettyPrinting()
                .registerTypeAdapter(Double.class, new JsonSerializer4Double())
                .registerTypeAdapter(Float.class, new JsonSerializer4Float())
                .registerTypeAdapter(BigDecimal.class, new JsonSerializer4BigDecimal())
                .registerTypeAdapter(Timestamp.class, new JsonSerializer4Timestamp())
                .registerTypeAdapter(java.sql.Date.class, new JsonSerializer4SqlDate())
                .registerTypeAdapter(java.util.Date.class, new JsonSerializer4UtilDate())
                .create();
        return gson;
    }


}
