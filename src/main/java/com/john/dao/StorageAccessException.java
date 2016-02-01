package com.john.dao;

public class StorageAccessException extends Exception {
    public StorageAccessException() {
        super();
    }

    public StorageAccessException(String message) {
        super(message);
    }

    public StorageAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public StorageAccessException(Throwable cause) {
        super(cause);
    }

    protected StorageAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
