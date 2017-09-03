package de.bergwerklabs.framework.commons.database.tablebuilder.column;

/**
 * Created by Benedikt on 30.07.2017.
 */
public class StringColumn extends Column {

    private final long maxLength;

    public StringColumn(String name, long maxLength, Object defaultValue, ColumnFlag... flags) {
        super(name, ColumnType.STRING, defaultValue, flags);
        this.maxLength = maxLength;
    }

    public long getMaxLength() {
        return maxLength;
    }
}
