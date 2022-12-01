package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Получение файлов конфигурции
 */
public class ConfProperties {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfProperties.class);

    protected static Properties properties = new Properties();


    static {
        try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.error("Error reading configuration file {}", e.getMessage());
        }
    }

    /**
     * Отдаем параметр по ключу из config.properties
     *
     * @param key - название параметра для получения
     * @return - параметр из файла config.properties
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}