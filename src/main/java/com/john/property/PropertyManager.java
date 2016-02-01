package com.john.property;

import com.john.service.PasswordService;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class PropertyManager {
    Logger log = org.slf4j.LoggerFactory.getLogger(PasswordService.class);
    protected Properties properties;

    protected PropertyManager(String resources) throws IOException {
        try (InputStream is = DBPropertyManager.class.getClassLoader().getResourceAsStream(resources)) {
            properties = new Properties();
            properties.load(is);
        }
    }

    public String getProperty(String key) throws PropertyManagerException {
        String result = properties.getProperty(key);
        if (result == null) {
            throw new PropertyManagerException("The key was not found");
        }
        return result;
    }
}
