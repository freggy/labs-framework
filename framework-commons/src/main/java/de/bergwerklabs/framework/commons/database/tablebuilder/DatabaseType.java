package de.bergwerklabs.framework.commons.database.tablebuilder;

import de.bergwerklabs.framework.commons.database.tablebuilder.column.*;

/**
 * Created by Benedikt on 30.07.2017.
 */
public enum DatabaseType {
    MySQL("com.mysql.jdbc.Driver", "jdbc:mysql"),
    PgSQL("org.postgresql.Driver", "jdbc:postgresql");

    private final String driverClass;
    private final String urlPrefix;

    DatabaseType(String driverClass, String urlPrefix) {
        this.driverClass = driverClass;
        this.urlPrefix = urlPrefix;
    }

    public String getAlterColumnString() {
        if (this == DatabaseType.MySQL) return "MODIFY COLUMN";
        return "ALTER COLUMN";
    }

    public String getDropPKString() {
        if (this == DatabaseType.PgSQL) return "DROP CONSTRAINT :table_pkey";
        return "DROP PRIMARY KEY";
    }

    public String getSQLFlag(ColumnFlag flag) {
        if (flag == ColumnFlag.NOT_NULL) {
            return "NOT NULL";
        } else if (flag == ColumnFlag.PRIMARY_KEY) {
            return "PRIMARY KEY";
        } else if (flag == ColumnFlag.AUTO_INCREMENT) {
            if (this == DatabaseType.PgSQL) return "SERIAL";
            return "AUTO_INCREMENT";
        }
        return "";
    }

    public String getSQLType(Column column) {
        ColumnType type = column.getType();
        switch (type) {
            case BYTE: return "TINYINT(1)";
            case LONG: return "BIGINT";
            case DATE: return "DATE";
            case TIME: return "TIME";
            case FLOAT: return "REAL";
            case SHORT: return "SMALLINT";
            case DOUBLE: return "FLOAT";
            case BOOLEAN: return "TINYINT(1)";
            case INTEGER: return "INTEGER";
            case TIMESTAMP: return "TIMESTAMP";
            case BIG_DECIMAL: return "DECIMAL";
            case STRING: {
                StringColumn strColumn = (StringColumn) column;
                long maxLength = strColumn.getMaxLength();

                if (maxLength <= StringColumnLength.TINY_TEXT) {
                    return "VARCHAR(" + String.valueOf(maxLength) + ")";
                } else if (maxLength <= StringColumnLength.TEXT) {
                    return "TEXT";
                } else if (maxLength <= StringColumnLength.MEDIUM_TEXT) {
                    return "MEDIUMTEXT";
                } else {
                    return "LONGTEXT";
                }
            }
            default: return "";
        }
    }

    public String getMutliPKConstraint(Table table, Column[] columns) {
        String result = "";
        if (this != DatabaseType.PgSQL) {
            result = "CONSTRAINT PK_" + table.getName() + " ";
        }

        result += getSQLFlag(ColumnFlag.PRIMARY_KEY) + " (";
        for (int i = 0; i < columns.length; i++) {
            Column column = columns[i];
            if (i > 0) result += ",";
            result += column.getName();
        }
        result += ")";

        return result;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }
}
