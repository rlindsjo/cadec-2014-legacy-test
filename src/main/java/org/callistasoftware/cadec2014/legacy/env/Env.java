package org.callistasoftware.cadec2014.legacy.env;

import java.io.IOException;
import java.util.Properties;

public class Env {

    private static Environment instance;
    
    public static void init() {
        if (instance != null) {
            throw new IllegalStateException("Environment already instantiated");
        }
        Properties properties = loadConfig("/application.properties");
        instance = new Environment(properties);
    }

    private static Properties loadConfig(String config) {
        Properties properties = new Properties();
        try {
            properties.load(Env.class.getResourceAsStream(config));
        } catch (IOException e) {
            throw new RuntimeException("Could not load " + config, e);
        }
        return properties;
    }
    
    public static Environment getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Environment not instantiated");
        }
        return instance;
    }
}
