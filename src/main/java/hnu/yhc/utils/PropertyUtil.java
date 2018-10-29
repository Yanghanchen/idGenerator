package hnu.yhc.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class PropertyUtil {
    private static URL url=PropertyUtil.class.getClassLoader().getResource("id.properties");

    public static String getProperty(String key) throws IOException {
        Properties properties=new Properties();
        properties.load(url.openStream());
        return properties.getProperty(key);
    }
}
