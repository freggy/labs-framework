package de.bergwerklabs.framework.commons.database.tablebuilder.statement;

import de.bergwerklabs.framework.commons.database.tablebuilder.column.ColumnType;
import de.bergwerklabs.framework.commons.database.tablebuilder.exception.SQLQueryException;
import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Created by Benedikt on 30.07.2017. */
public class Statement implements Closeable {

  private final Connection connection;
  private final PreparedStatement stmt;

  public Statement(Connection connection, PreparedStatement stmt) {
    this.connection = connection;
    this.stmt = stmt;
  }

  /**
   * Sets the arguments for the prepared statement.
   *
   * @param arguments the arguments
   * @throws IllegalArgumentException if an argument is invalid
   * @throws SQLQueryException if unable to set an argument
   */
  private void setArguments(Object... arguments) {
    for (int i = 0; i < arguments.length; i++) {
      int index = i + 1;

      Object param = arguments[i];
      if (param == null) {
        try {
          stmt.setObject(index, null);
        } catch (SQLException e) {
          throw new SQLQueryException("Unable to set argument " + i + ".", e);
        }
        continue;
      }

      ColumnType type = ColumnType.findByClass(param.getClass());

      if (type == null) {
        throw new IllegalArgumentException(
            "The type of argument " + i + " is not allowed. " + param.getClass() + " given.");
      }

      try {
        stmt.setObject(index, type.getJavaClass().cast(param));
      } catch (SQLException e) {
        throw new SQLQueryException("Unable to set argument " + i + ".", e);
      }
    }
  }

  /**
   * Executes a update, insert or delete query. Call <code>close()</code> when done using the
   * prepared statement.
   *
   * @param arguments the arguments
   * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0
   *     for SQL statements that return nothing
   * @throws SQLQueryException if unable to execute query
   */
  public int executeUpdate(Object... arguments) {
    setArguments(arguments);

    try {
      return stmt.executeUpdate();
    } catch (SQLException e) {
      throw new SQLQueryException("Unable to execute update query.", e);
    }
  }

  /**
   * Executes a query and fetches data. Call <code>close()</code> when done using the prepared
   * statement.
   *
   * @param arguments the arguments
   * @return
   * @throws SQLQueryException if unable to execute query
   */
  public StatementResult execute(Object... arguments) {
    setArguments(arguments);

    ResultSet set = null;
    try {
      stmt.execute();
      set = stmt.getResultSet();

      List<String> columns = new ArrayList<>();
      List<Map<String, Object>> rows = new ArrayList<>();
      while (set.next()) {
        Map<String, Object> values = new HashMap<>();
        for (int c = 0; c < set.getMetaData().getColumnCount(); c++) {
          values.put(set.getMetaData().getColumnName(c + 1), set.getObject(c + 1));
        }
        rows.add(values);
      }

      for (int i = 0; i < set.getMetaData().getColumnCount(); i++) {
        columns.add(set.getMetaData().getColumnName(i + 1));
      }

      return new StatementResult(columns, rows);
    } catch (SQLException e) {
      throw new SQLQueryException("Unable to execute query.", e);
    } finally {
      if (set != null)
        try {
          set.close();
        } catch (SQLException ignored) {
        }
    }
  }

  /** Closes the connection to the database as well as the prepared statement. */
  @Override
  public void close() {
    try {
      stmt.close();
    } catch (SQLException ignored) {
    }
    try {
      connection.close();
    } catch (SQLException ignored) {
    }
  }

  @Override
  public String toString() {
    return stmt.toString();
  }
}
