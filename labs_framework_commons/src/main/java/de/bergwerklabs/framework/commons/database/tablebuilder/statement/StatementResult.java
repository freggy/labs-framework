package de.bergwerklabs.framework.commons.database.tablebuilder.statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Benedikt on 30.07.2017.
 */
public class StatementResult {

    private final List<String> columns = new ArrayList<>();
    private final List<Row> rows = new ArrayList<>();

    public StatementResult(List<String> columns, List<Map<String, Object>> rows) {
        this.columns.addAll(columns);
        for (Map<String, Object> row : rows) {
            this.rows.add(new Row(columns, row));
        }
    }

    public int getColumnCount() {
        return columns.size();
    }

    public List<String> getColumns() {
        return columns;
    }

    public String getColumnName(int column) {
        return columns.get(column);
    }

    public int getRowCount() {
        return rows.size();
    }

    public Row getRow(int index) {
        return rows.get(index);
    }

    public Row[] getRows() {
        return rows.toArray(new Row[0]);
    }

    public boolean isEmpty() {
        return rows.size() == 0;
    }
}
