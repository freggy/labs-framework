package de.bergwerklabs.framework.commons.database.tablebuilder;

import de.bergwerklabs.framework.commons.database.tablebuilder.column.Column;
import de.bergwerklabs.framework.commons.database.tablebuilder.column.ColumnFlag;
import de.bergwerklabs.framework.commons.database.tablebuilder.column.ColumnType;
import de.bergwerklabs.framework.commons.database.tablebuilder.column.StringColumn;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Benedikt on 29.07.2017.
 */
public class TableBuilder {

    private final String name;
    private final Database database;
    private final List<Column> columns = new ArrayList<>();

    /**
     * Creates a new <code>TableBuilder</code>.
     * @param database the database
     * @param name the name of the table
     */
    public TableBuilder(Database database, String name) {
        this.database = database;
        this.name = name;
    }

    /**
     * Creates a <code>Table</code> instance.
     * @return the built table
     */
    public Table build() {
        return new Table(name, columns, database);
    }

    //
    // BOOLEAN
    //

    public TableBuilder addBooleanColumn(String name, ColumnFlag... flags) {
        return addBooleanColumn(name, null, flags);
    }

    public TableBuilder addBooleanColumn(String name, Boolean defaultValue, ColumnFlag... flags) {
        columns.add(new Column(name, ColumnType.BOOLEAN, defaultValue, flags));
        return this;
    }

    //
    // BYTE
    //

    public TableBuilder addByteColumn(String name, ColumnFlag... flags) {
        return addByteColumn(name, null, flags);
    }

    public TableBuilder addByteColumn(String name, Byte defaultValue, ColumnFlag... flags) {
        columns.add(new Column(name, ColumnType.BYTE, defaultValue, flags));
        return this;
    }

    //
    // SHORT
    //

    public TableBuilder addShortColumn(String name, ColumnFlag... flags) {
        return addShortColumn(name, null, flags);
    }

    public TableBuilder addShortColumn(String name, Short defaultValue, ColumnFlag... flags) {
        columns.add(new Column(name, ColumnType.SHORT, defaultValue, flags));
        return this;
    }

    //
    // INTEGER
    //

    public TableBuilder addIntegerColumn(String name, ColumnFlag... flags) {
        return addIntegerColumn(name, null, flags);
    }

    public TableBuilder addIntegerColumn(String name, Integer defaultValue, ColumnFlag... flags) {
        columns.add(new Column(name, ColumnType.INTEGER, defaultValue, flags));
        return this;
    }

    //
    // LONG
    //

    public TableBuilder addLongColumn(String name, ColumnFlag... flags) {
        return addLongColumn(name, null, flags);
    }

    public TableBuilder addLongColumn(String name, Long defaultValue, ColumnFlag... flags) {
        columns.add(new Column(name, ColumnType.LONG, defaultValue, flags));
        return this;
    }

    //
    // FLOAT
    //

    public TableBuilder addFloatColumn(String name, ColumnFlag... flags) {
        return addFloatColumn(name, null, flags);
    }

    public TableBuilder addFloatColumn(String name, Float defaultValue, ColumnFlag... flags) {
        columns.add(new Column(name, ColumnType.FLOAT, defaultValue, flags));
        return this;
    }

    //
    // DOUBLE
    //

    public TableBuilder addDoubleColumn(String name, ColumnFlag... flags) {
        return addDoubleColumn(name, null, flags);
    }

    public TableBuilder addDoubleColumn(String name, Double defaultValue, ColumnFlag... flags) {
        columns.add(new Column(name, ColumnType.DOUBLE, defaultValue, flags));
        return this;
    }

    //
    // BIG DECIMAL
    //

    public TableBuilder addBigDecimalColumn(String name, ColumnFlag... flags) {
        return addBigDecimalColumn(name, null, flags);
    }

    public TableBuilder addBigDecimalColumn(String name, BigDecimal defaultValue, ColumnFlag... flags) {
        columns.add(new Column(name, ColumnType.BIG_DECIMAL, defaultValue, flags));
        return this;
    }

    //
    // STRING
    //

    public TableBuilder addStringColumn(String name, int maxLength, ColumnFlag... flags) {
        return addStringColumn(name, maxLength, null, flags);
    }

    public TableBuilder addStringColumn(String name, int maxLength, String defaultValue, ColumnFlag... flags) {
        columns.add(new StringColumn(name, maxLength, defaultValue, flags));
        return this;
    }

    //
    // TIME
    //

    public TableBuilder addTimeColumn(String name, ColumnFlag... flags) {
        return addTimeColumn(name, null, flags);
    }

    public TableBuilder addTimeColumn(String name, Time defaultValue, ColumnFlag... flags) {
        columns.add(new Column(name, ColumnType.TIME, defaultValue, flags));
        return this;
    }

    //
    // DATE
    //

    public TableBuilder addDateColumn(String name, ColumnFlag... flags) {
        return addDateColumn(name, null, flags);
    }

    public TableBuilder addDateColumn(String name, Date defaultValue, ColumnFlag... flags) {
        columns.add(new Column(name, ColumnType.DATE, defaultValue, flags));
        return this;
    }

    //
    // TIMESTAMP
    //

    public TableBuilder addTimestampColumn(String name, ColumnFlag... flags) {
        return addTimestampColumn(name, null, flags);
    }

    public TableBuilder addTimestampColumn(String name, Timestamp defaultValue, ColumnFlag... flags) {
        columns.add(new Column(name, ColumnType.TIMESTAMP, defaultValue, flags));
        return this;
    }
}
