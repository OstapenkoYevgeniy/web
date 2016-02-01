package com.john.dbcp;

import com.john.dao.StorageAccess;
import com.john.dao.StorageAccessException;
import com.john.property.DBPropertyManager;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ConnectionPool implements StorageAccess<Connection> {
    Logger log = org.slf4j.LoggerFactory.getLogger(ConnectionPool.class);
    private static volatile ConnectionPool instance;
    private final BlockingQueue<Connection> connections = new LinkedBlockingQueue<>();

    private static String JDBC_URL;
    private static String DB_USER;
    private static String DB_PASSWORD;

    private ConnectionPool() throws StorageAccessException {
        String JDBC_DRIVER;
        int MAX_CONNECTION_COUNT;
        try {
            DBPropertyManager dbProperty = DBPropertyManager.getInstance();

            JDBC_DRIVER = dbProperty.getProperty("JDBC_DRIVER");
            JDBC_URL = dbProperty.getProperty("JDBC_URL");
            DB_USER = dbProperty.getProperty("DB_USER");
            DB_PASSWORD = dbProperty.getProperty("DB_PASSWORD");
            MAX_CONNECTION_COUNT = Integer.valueOf(dbProperty.getProperty("MAX_CONNECTION_COUNT"));
        } catch (Exception e) {
            log.error("", e);
            throw new StorageAccessException(e);
        }
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            log.error("", e);
            throw new StorageAccessException("The driver is not loaded", e);
        }
        for (int i = 0; i < MAX_CONNECTION_COUNT; i++) {
            createConnection();
        }
    }

    public static ConnectionPool getInstance() throws StorageAccessException {
        ConnectionPool localInstance = instance;
        if (localInstance == null) {
            synchronized (DBPropertyManager.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectionPool();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Connection getConnection() throws StorageAccessException {
        try {
            return connections.poll(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("", e);
            throw new StorageAccessException(e);
        }
    }

    @Override
    public void returnConnection(Connection connection) throws StorageAccessException {
        if (connection == null) {
            createConnection();
            return;
        }
        try {
            if (connection.isValid(5)) {
                connections.add(connection);
            } else {
                createConnection();
            }
        } catch (SQLException e) {
            log.error("", e);
            throw new StorageAccessException(e);
        }
    }

    private void createConnection() throws StorageAccessException {
        try {
            connections.add(DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD));
        } catch (SQLException e) {
            log.error("", e);
            throw new StorageAccessException(e);
        }
    }
}
