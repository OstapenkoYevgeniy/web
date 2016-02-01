package com.john.property;

public class PropertyManagerException extends Exception {
    public PropertyManagerException() {
        super();
    }

    public PropertyManagerException(String message) {
        super(message);
    }

    public PropertyManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertyManagerException(Throwable cause) {
        super(cause);
    }

    protected PropertyManagerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
