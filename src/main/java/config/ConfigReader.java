package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private static final String DEFAULT_CONFIG_PATH = "src/test/resources/configs/config.properties";

    static {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream(DEFAULT_CONFIG_PATH);
            properties.load(fis);
            fis.close();

            // Load environment specific properties
            String env = System.getProperty("env", properties.getProperty("env"));
            String envPath = "src/test/resources/configs/" + env + ".properties";
            FileInputStream envFis = new FileInputStream(envPath);
            properties.load(envFis);
            envFis.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration files could not be found or loaded.");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
