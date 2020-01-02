package uz.tuit.config;

import java.io.IOException;
import java.util.Properties;

public class Config {

    private Properties properties;

    public Config() {
        properties = new Properties();
        try {
            properties.load((getClass().getClassLoader().getResourceAsStream("application.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDatabaseUser() {
        return properties.getProperty("database.user");
    }

    public String getDatabasePassword() {
        return properties.getProperty("database.password");
    }

    public String getDatabaseUrl() {
        return properties.getProperty("database.url");
    }
}
