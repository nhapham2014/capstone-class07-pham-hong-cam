package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try {
            // 1. Load config mặc định
            loadFile("config.properties");

            // 2. Xác định env (ưu tiên command line)
            String env = System.getProperty(
                    "env",
                    properties.getProperty("env", "dev")
            );

            // 3. Load config theo env
            loadFile("config-" + env + ".properties");

        } catch (Exception e) {
            throw new RuntimeException("Cannot load config files", e);
        }
    }

    private static void loadFile(String fileName) throws Exception {
        try (InputStream is = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream(fileName)) {

            if (is == null) {
                throw new RuntimeException("File not found: " + fileName);
            }
            properties.load(is);
        }
    }

    public static String get(String key) {
        // ƯU TIÊN command line → rồi tới file
        return System.getProperty(key, properties.getProperty(key));
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
