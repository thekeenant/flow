package com.keenant.flow;

import com.keenant.flow.exp.ParamExp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Insert {

  private Exp table;
  private final List<Map<String, Exp>> records;

  public Insert(Exp table) {
    this.table = table;
    records = new ArrayList<>();
    records.add(new HashMap<>());
  }

  private Map<String, Exp> currentRecord() {
    return records.get(records.size() - 1);
  }

  private Set<String> columnNames() {
    Set<String> names = new HashSet<>();
    for (Map<String, Exp> record : records) {
      names.addAll(record.keySet());
    }
    return names;
  }

  public Insert cpy() {
    Insert insert = new Insert(table);
    for (Map<String, Exp> record : records) {
      insert.records.add(new HashMap<>(record));
    }
    return insert;
  }

  public Insert table(Exp table) {
    this.table = table;
    return this;
  }

  public Insert with(String field, Exp value) {
    currentRecord().put(field, value);
    return this;
  }

  public Insert with(String field, Object value) {
    return with(field, new ParamExp(value));
  }

  public <T> Insert with(Column<T> column, T value) {
    return with(column.getName(), value);
  }

  public Insert nextRecord() {
    records.add(new HashMap<>());
    return this;
  }

  public QueryPart build(SQLDialect dialect) {
    StringBuilder sql = new StringBuilder();
    List<Object> params = new ArrayList<>();

    Set<String> columns = columnNames();

    sql.append("INSERT INTO ");

    {
      QueryPart tablePart = table.build(dialect);
      sql.append(tablePart.getSql());
      params.addAll(tablePart.getParams());
    }

    sql.append(" (");
    for (String column : columns) {
      sql.append(column);
      sql.append(",");
    }
    sql.deleteCharAt(sql.length() - 1);
    sql.append(") VALUES ");

    for (Map<String, Exp> record : records) {
      sql.append("(");
      for (String column : columns) {
        QueryPart curr = record.get(column).build(dialect);
        sql.append(curr.getSql());
        params.addAll(curr.getParams());
        sql.append(",");
      }
      sql.deleteCharAt(sql.length() - 1);
      sql.append("),");
    }
    sql.deleteCharAt(sql.length() - 1);

    return new QueryPart(sql.toString(), params);
  }

  public Result executeAndReturn(DatabaseContext database, SQLDialect dialect) {
    QueryPart part = build(dialect);
    Query query = database.prepareUpdate(part);
    return query.execute();
  }

  public void execute(DatabaseContext database, SQLDialect dialect) {
    executeAndReturn(database, dialect);
  }
}
