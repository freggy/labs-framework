package de.bergwerklabs.framework.commons.database.tablebuilder;

import de.bergwerklabs.framework.commons.database.tablebuilder.column.Column;
import de.bergwerklabs.framework.commons.database.tablebuilder.column.ColumnFlag;
import de.bergwerklabs.framework.commons.database.tablebuilder.exception.SQLArgumentException;
import de.bergwerklabs.framework.commons.database.tablebuilder.statement.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benedikt on 30.07.2017.
 */
public class Table {

    private final String name;
    private final Database database;

    private final List<Column> columns = new ArrayList<>();

    public Table(String name, List<Column> columns, Database database) {
        this.name = name;
        this.database = database;
        this.columns.addAll(columns);
    }

    /**
     * Inserts a new row in the table.
     * @param arguments the column values
     * @throws SQLArgumentException if the argument count is invalid or an argument has an invalid value
     */
    public void insertRow(Object... arguments) {
        if (arguments.length != columns.size()) {
            throw new SQLArgumentException("Invalid arguments count. " + columns.size() + " expected. " + arguments.length + " given.");
        }

        for (int i = 0; i < arguments.length; i++) {
            if (arguments[i] == null) continue;

            Class columnClass = columns.get(i).getType().getJavaClass();
            if (columnClass != arguments[i].getClass()) {
                throw new SQLArgumentException("Argument with index " + i + " (" + columns.get(i).getName() + ") has to be of type " + columnClass.getName() + ". " + arguments[i].getClass().getName() + " given.");
            }
        }

        StringBuilder values = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            if (i > 0) {
                values.append(", ");
            }
            values.append("?");
        }

        Statement statement = prepareStatement("INSERT INTO :table VALUES (" + values.toString() + ")");
        statement.executeUpdate(arguments);
        statement.close();
    }

    /**
     * Creates a prepared statement. <code>:table</code> will be replaced with the table name. <code>:&lt;index&gt;</code>
     * will be replaced with the corresponding column name.
     *
     * @param query the query
     * @return the prepared statement
     */
    public Statement prepareStatement(String query) {
        query = query.replaceAll(":table", name);
        for (int i = 0; i < columns.size(); i++) {
            query = query.replaceAll(":" + i, columns.get(i).getName());
        }
        return database.prepareStatement(query);
    }

    public void ensureCreated() {
        database.ensureCreated(this);
    }

    public void dropIfExists() {
        database.dropTable(this);
        database.createTable(this);
    }

    public void alterDropColumns() {
        database.alterTable(this, false);
    }

    public void alterKeepColumns() {
        database.alterTable(this, true);
    }

    public Column[] getColumns() {
        return columns.toArray(new Column[0]);
    }

    public Column getColumn(int index) {
        return columns.get(index);
    }

    public String getName() {
        return name;
    }

    public Database getDatabase() {
        return database;
    }

    public Column findColumn(String name) {
        for (Column column : columns) {
            if (column.getName().equalsIgnoreCase(name)) return column;
        }
        return null;
    }

    public Column[] getPrimaryColumns() {
        List<Column> primaryColumns = new ArrayList<>();
        for (Column column : columns) {
            if (column.hasFlag(ColumnFlag.PRIMARY_KEY)) {
                primaryColumns.add(column);
            }
        }
        return primaryColumns.toArray(new Column[0]);
    }
}
