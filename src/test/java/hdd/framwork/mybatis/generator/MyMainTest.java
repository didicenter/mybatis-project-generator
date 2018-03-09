package hdd.framwork.mybatis.generator;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * Created by SongWei on 09/03/2018.
 */
public class MyMainTest {

    @Test
    public void testJsonParse() {

            System.out.println(JSONObject.parseObject(null));
            System.out.println(JSONObject.parseObject("null"));
            System.out.println(JSONObject.parseObject("{}"));
            System.out.println(JSONObject.parseObject(""));

    }

}
