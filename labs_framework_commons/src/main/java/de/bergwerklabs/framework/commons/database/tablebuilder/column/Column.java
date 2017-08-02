package de.bergwerklabs.framework.commons.database.tablebuilder.column;

import de.bergwerklabs.framework.commons.database.tablebuilder.exception.SQLSchemeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Benedikt on 29.07.2017.
 */
public class Column {

    private final String name;
    private final ColumnType type;
    private final Object defaultValue;

    private final List<ColumnFlag> flags = new ArrayList<>();

    public Column(String name, ColumnType type, Object defaultValue, ColumnFlag... flags) {
        this.name = name;
        this.type = type;
        this.defaultValue = defaultValue;

        this.flags.addAll(Arrays.asList(flags));

        if (defaultValue != null && defaultValue.getClass() != type.getJavaClass()) {
            throw new SQLSchemeException("The default value type does not equal the column type.");
        }
    }

    public String getName() {
        return name;
    }

    public ColumnType getType() {
        return type;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public List<ColumnFlag> getFlags() {
        return flags;
    }

    public boolean hasFlag(ColumnFlag flag) {
        return flags.contains(flag);
    }
}
