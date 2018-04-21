package de.bergwerklabs.framework.commons.database.tablebuilder.exception;

/** Created by Benedikt on 30.07.2017. */
public class SQLSchemeException extends RuntimeException {

  public SQLSchemeException() {
    super();
  }

  public SQLSchemeException(String message) {
    super(message);
  }

  public SQLSchemeException(String message, Throwable cause) {
    super(message, cause);
  }
}
