package de.bergwerklabs.framework.commons.database.tablebuilder.exception;

/**
 * Created by Benedikt on 30.07.2017.
 */
public class SQLArgumentException extends RuntimeException {

    public SQLArgumentException() {
        super();
    }

    public SQLArgumentException(String message) {
        super(message);
    }

    public SQLArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
