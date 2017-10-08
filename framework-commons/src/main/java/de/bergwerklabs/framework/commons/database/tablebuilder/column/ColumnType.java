package de.bergwerklabs.framework.commons.database.tablebuilder.column;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by Benedikt on 29.07.2017.
 */
public enum ColumnType {
    BOOLEAN(Boolean.class),
    BYTE(Byte.class),
    SHORT(Short.class),
    INTEGER(Integer.class),
    LONG(Long.class),
    FLOAT(Float.class),
    DOUBLE(Double.class),
    BIG_DECIMAL(BigDecimal.class),
    STRING(String.class),
    DATE(Date.class),
    TIME(Time.class),
    TIMESTAMP(Timestamp.class);

    private final Class type;

    ColumnType(Class type) {
        this.type = type;
    }

    public Class getJavaClass() {
        return type;
    }

    public static ColumnType findByClass(Class clazz) {
        for (ColumnType type : ColumnType.values()) {
            if (type.getJavaClass() == clazz) return type;
        }
        return null;
    }
}
