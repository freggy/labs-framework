package de.bergwerklabs.framework.commons.database.tablebuilder.statement;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Created by Benedikt on 30.07.2017. */
public class Row {

  private List<String> columns = new ArrayList<>();
  private Map<String, Object> values = new HashMap<>();

  public Row(List<String> columns, Map<String, Object> values) {
    this.columns.addAll(columns);
    this.values.putAll(values);
  }

  public Object getObject(String column) {
    return values.get(column);
  }

  public Boolean getBoolean(String column) {
    return (Boolean) getObject(column);
  }

  public Byte getByte(String column) {
    return (Byte) getObject(column);
  }

  public Short getShort(String column) {
    return (Short) getObject(column);
  }

  public Integer getInteger(String column) {
    return (Integer) getObject(column);
  }

  public Long getLong(String column) {
    return (Long) getObject(column);
  }

  public Float getFloat(String column) {
    return (Float) getObject(column);
  }

  public Double getDouble(String column) {
    return (Double) getObject(column);
  }

  public BigDecimal getBigDecimal(String column) {
    return (BigDecimal) getObject(column);
  }

  public String getString(String column) {
    return (String) getObject(column);
  }

  public Date getDate(String column) {
    return (Date) getObject(column);
  }

  public Time getTime(String column) {
    return (Time) getObject(column);
  }

  public Timestamp getTimestamp(String column) {
    return (Timestamp) getObject(column);
  }

  public Object getObject(int column) {
    return values.get(columns.get(column));
  }

  public Boolean getBoolean(int column) {
    return (Boolean) getObject(column);
  }

  public Byte getByte(int column) {
    return (Byte) getObject(column);
  }

  public Short getShort(int column) {
    return (Short) getObject(column);
  }

  public Integer getInteger(int column) {
    return (Integer) getObject(column);
  }

  public Long getLong(int column) {
    return (Long) getObject(column);
  }

  public Float getFloat(int column) {
    return (Float) getObject(column);
  }

  public Double getDouble(int column) {
    return (Double) getObject(column);
  }

  public BigDecimal getBigDecimal(int column) {
    return (BigDecimal) getObject(column);
  }

  public String getString(int column) {
    return (String) getObject(column);
  }

  public Date getDate(int column) {
    return (Date) getObject(column);
  }

  public Time getTime(int column) {
    return (Time) getObject(column);
  }

  public Timestamp getTimestamp(int column) {
    return (Timestamp) getObject(column);
  }
}
