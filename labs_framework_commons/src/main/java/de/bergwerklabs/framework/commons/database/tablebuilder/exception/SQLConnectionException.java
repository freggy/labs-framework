package de.bergwerklabs.framework.commons.database.tablebuilder.exception;

/**
 * Created by Benedikt on 30.07.2017.
 */
public class SQLConnectionException extends RuntimeException {
    public SQLConnectionException() {
    }

    public SQLConnectionException(String message) {
        super(message);
    }

    public SQLConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
