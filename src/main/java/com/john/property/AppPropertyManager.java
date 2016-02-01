package com.john.property;

import java.io.IOException;

public class AppPropertyManager extends PropertyManager {
    private static volatile AppPropertyManager instance;

    private AppPropertyManager() throws IOException {
        super("app.property");
    }

    public static AppPropertyManager getInstance() throws IOException {
        AppPropertyManager localInstance = instance;
        if (localInstance == null) {
            synchronized (AppPropertyManager.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new AppPropertyManager();
                }
            }
        }
        return localInstance;
    }
}
