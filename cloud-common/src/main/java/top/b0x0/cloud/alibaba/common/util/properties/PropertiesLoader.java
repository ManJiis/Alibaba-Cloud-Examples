package top.b0x0.cloud.alibaba.common.util.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取properties配置文件信息
 *
 * @author TANG
 * @date 2021-02-13
 */
public class PropertiesLoader {

    private static final Logger log = LoggerFactory.getLogger(PropertiesLoader.class);

    private static final Properties PROPERTIES = new Properties();

    static {
        try {
            PROPERTIES.load(PropertiesLoader.class.getClassLoader().getResourceAsStream("application.properties"));
            log.info("ActEnv --> : {}", PROPERTIES.getProperty("oss.profiles.active"));
        } catch (Exception e) {
            log.warn("Load Properties error : {}", e.getMessage());
        }
    }

    public static Properties getProperties() {
        return PROPERTIES;
    }

    public static Properties getProperties(String propertyName) {
        Properties properties = new Properties();
        try {
            properties.load(PropertiesLoader.class.getClassLoader().getResourceAsStream(propertyName + ".properties"));
        } catch (IOException e) {
            log.warn("Load Properties error : {}", e.getMessage(), e.fillInStackTrace());
        }
        return properties;
    }
}