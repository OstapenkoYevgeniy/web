package com.john.property;

import java.io.IOException;

public class SqlQueryPropertyManager extends PropertyManager {
    private static volatile SqlQueryPropertyManager instance;

    protected SqlQueryPropertyManager() throws IOException {
        super("sql_query.property");
    }

    public static SqlQueryPropertyManager getInstance() throws IOException {
        SqlQueryPropertyManager localInstance = instance;
        if (localInstance == null) {
            synchronized (SqlQueryPropertyManager.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SqlQueryPropertyManager();
                }
            }
        }
        return localInstance;
    }
}
