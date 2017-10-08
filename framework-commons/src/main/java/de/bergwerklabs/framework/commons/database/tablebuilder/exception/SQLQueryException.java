package de.bergwerklabs.framework.commons.database.tablebuilder.exception;

/**
 * Created by Benedikt on 30.07.2017.
 */
public class SQLQueryException extends RuntimeException {

    public SQLQueryException() {
        super();
    }

    public SQLQueryException(String message) {
        super(message);
    }

    public SQLQueryException(String message, Throwable cause) {
        super(message, cause);
    }
}
