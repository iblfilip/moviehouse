package com.ibl.moviehouse.tools;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Service
public class PropertiesHandler {

    Properties prop;
    public static final String PROP_CLIENT_ID = "CLIENT_ID";
    public static final String PROP_PROJECT_ID = "PROJECT_ID";
    public static final String PROP_COOKIE_NAME = "COOKIE_NAME";
    public static final String PROP_SERVICE_ACCOUNT_EMAIL = "SERVICE_ACCOUNT_ID";
    public static final String PROP_WIDGET_URL = "WIDGET_URL";
    public static final String PROP_KEY_FILE = "KEY_FILE";
    public static final String CONFIG_FILE = "gitkit.properties";

    private PropertiesHandler() {
        prop = getProperties();
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

    public String getClientId() {
        return prop.getProperty(PROP_CLIENT_ID);
    }

    public String getProjectId() {
        return prop.getProperty(PROP_PROJECT_ID);
    }

    public String getCookieName() {
        return prop.getProperty(PROP_COOKIE_NAME);
    }

    public String getServiceAccountEmail() {
        return prop.getProperty(PROP_SERVICE_ACCOUNT_EMAIL);
    }

    public String getWidgetUrl() {
        return prop.getProperty(PROP_WIDGET_URL);
    }

    public String getKeyFile() {
        return prop.getProperty(PROP_KEY_FILE);
    }
}
