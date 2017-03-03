package com.ibl.moviehouse.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHandler {

    Properties prop;
    public static final String PROP_DB_URL = "db_url";
    public static final String CONFIG_FILE = "config.properties";

    private PropertiesHandler() {
        prop = getProperties();
    }

    private static PropertiesHandler instance = new PropertiesHandler();

    public static PropertiesHandler getInstance() {
        return instance;
    }


    public Properties getProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE);
            // load a properties file
            prop.load(inputStream);
            // get the property value

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }

    public String getDbUrl() {
        return prop.getProperty(PROP_DB_URL);
    }
}
