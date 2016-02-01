package com.john.property;

import java.io.IOException;

public class DBPropertyManager extends PropertyManager {
    private static volatile DBPropertyManager instance;

    private DBPropertyManager() throws IOException {
        super("db.property");
    }

    public static DBPropertyManager getInstance() throws IOException {
        DBPropertyManager localInstance = instance;
        if (localInstance == null) {
            synchronized (DBPropertyManager.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DBPropertyManager();
                }
            }
        }
        return localInstance;
    }
}
