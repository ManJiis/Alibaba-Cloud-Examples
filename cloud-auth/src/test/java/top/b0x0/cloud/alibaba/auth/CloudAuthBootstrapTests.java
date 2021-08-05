package top.b0x0.cloud.alibaba.auth;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.b0x0.cloud.alibaba.common.util.properties.PropertiesLoader;

import java.util.Properties;

@SpringBootTest
class CloudAuthBootstrapTests {

    @Test
    void contextLoads() {
        Properties properties = PropertiesLoader.getProperties("default-user");
        for (Object o : properties.keySet()) {
            System.out.println(o + " = " + properties.getProperty(o.toString()));
        }

        JSONArray jsonArray = (JSONArray) JSONObject.parse(properties.get("default.users").toString());
        for (Object o : jsonArray) {
            System.out.println("o = " + o);
        }
    }

}
