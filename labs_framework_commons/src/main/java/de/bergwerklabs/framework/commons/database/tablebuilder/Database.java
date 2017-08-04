package de.bergwerklabs.framework.commons.database.tablebuilder;

import de.bergwerklabs.framework.commons.database.tablebuilder.column.Column;
import de.bergwerklabs.framework.commons.database.tablebuilder.column.ColumnFlag;
import de.bergwerklabs.framework.commons.database.tablebuilder.exception.SQLConnectionException;
import de.bergwerklabs.framework.commons.database.tablebuilder.exception.SQLQueryException;
import de.bergwerklabs.framework.commons.database.tablebuilder.statement.Row;
import de.bergwerklabs.framework.commons.database.tablebuilder.statement.Statement;
import de.bergwerklabs.framework.commons.database.tablebuilder.statement.StatementResult;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Benedikt on 30.07.2017.
 */
public class Database {

    private final DatabaseType type;

    private final String host;
    private final String database;
    private final String username;
    private final String password;

    /**
     * Creates a new database instance
     * @param type database driver/type
     * @param host sql host
     * @param database database name
     * @param username database username
     * @param password database password
     */
    public Database(DatabaseType type, String host, String database, String username, String password) {
        if (type == null) {
            throw new SQLConnectionException("The database type cannot be null.");
        }

        this.type = type;
        this.host = host;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    /**
     * Returns a new connection for this database.
     * @return the connection
     * @throws SQLConnectionException if unable to create the connection
     */
    private Connection getConnection() {
        try {
            Class.forName(type.getDriverClass());
            return DriverManager.getConnection(type.getUrlPrefix() + "://" + host + "/" + database, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new SQLConnectionException("Unable to connect to database.", e);
        }
    }

    /**
     * Creates a prepared statement.
     * @param query the query
     * @return the prepared statement
     * @throws SQLQueryException if unable to prepare statement
     */
    public Statement prepareStatement(String query) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            return new Statement(connection, statement);
        } catch (SQLException e) {
            throw new SQLQueryException("Unable to prepare statement.", e);
        }
    }

    /**
     * Returns a new <code>TableBuilder</code> for this database.
     * @param name the name of the table
     * @return the <code>TableBuilder</code>
     */
    public TableBuilder getTableBuilder(String name) {
        return new TableBuilder(this, name);
    }

    StatementResult getTableSchema(Table table) {
        Statement statement = table.prepareStatement("SELECT * FROM INFORMATION_SCHEMA.columns WHERE table_name = ':table' ORDER BY ordinal_position");
        StatementResult result = statement.execute();
        statement.close();
        return result;
    }

    void ensureCreated(Table table) {
        if (getTableSchema(table).getRowCount() > 0) return;
        createTable(table);
    }

    void dropTable(Table table) {
        Statement statement = table.prepareStatement("DROP TABLE :table");
        statement.executeUpdate();
        statement.close();
    }

    void alterTable(Table table, boolean keep) {
        StatementResult schema = getTableSchema(table);

        StringBuilder query = new StringBuilder("ALTER TABLE :table");

        List<Column> columns = new ArrayList<>();
        Collections.addAll(columns, table.getColumns());

        List<Object> defaultValues = new ArrayList<>();

        for (Row row : schema.getRows()) {
            Column column = table.findColumn(row.getString("COLUMN_NAME"));

            if (column == null) {
                if (!keep) query.append(" DROP COLUMN ").append(row.getString("COLUMN_NAME")).append(",");
                continue;
            }

            query.append(" ").append(this.type.getAlterColumnString()).append(" ").append(column.getName()).append(" ").append(getColumnDefinition(column, false)).append(",");

            if (column.getDefaultValue() != null) defaultValues.add(column.getDefaultValue());
            columns.remove(column);
        }

        for (Column column : columns) {
            query.append(" ADD ").append(getColumnDefinition(column, false));
            if (column.getDefaultValue() != null) defaultValues.add(column.getDefaultValue());
        }

        Column[] primaryColumns = table.getPrimaryColumns();

        query.append(" ").append(this.type.getDropPKString()).append(",");
        if (primaryColumns.length == 1) {
            query.append(" ADD ").append(this.type.getSQLFlag(ColumnFlag.PRIMARY_KEY)).append(" (").append(primaryColumns[0].getName()).append(")");
        } else if (primaryColumns.length > 1) {
            query.append(" ADD ").append(this.type.getMutliPKConstraint(table, primaryColumns));
        }

        Statement statement = table.prepareStatement(query.toString());
        statement.executeUpdate(defaultValues.toArray(new Object[0]));
        statement.close();
    }

    void createTable(Table table) {
        Column[] columns = table.getColumns();
        Column[] primaryColumns = table.getPrimaryColumns();
        List<Object> defaultValues = new ArrayList<>();

        StringBuilder query = new StringBuilder("CREATE TABLE :table (");
        for (int i = 0; i < columns.length; i++) {
            query.append(":").append(i);
            query.append(" ").append(getColumnDefinition(columns[i], primaryColumns.length == 1));
            if (i + 1 < columns.length) {
                query.append(", ");
            }
            if (columns[i].getDefaultValue() != null) defaultValues.add(columns[i].getDefaultValue());
        }

        if (primaryColumns.length > 1) {
            query.append(", ").append(this.type.getMutliPKConstraint(table, primaryColumns));
        }
        query.append(");");

        Statement stmt = table.prepareStatement(query.toString());
        stmt.executeUpdate(defaultValues.toArray(new Object[0]));
        stmt.close();
    }

    private String getColumnDefinition(Column column, boolean primaryKey) {
        StringBuilder definition = new StringBuilder(this.type.getSQLType(column));
        for (ColumnFlag flag : column.getFlags()) {
            if (flag == ColumnFlag.PRIMARY_KEY && !primaryKey) continue;
            definition.append(" ").append(this.type.getSQLFlag(flag));
        }
        if (column.getDefaultValue() != null) definition.append(" DEFAULT ?");
        return definition.toString();
    }
}
