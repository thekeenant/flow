package com.keenant.flow;

import static com.keenant.flow.Flow.field;

import com.keenant.flow.exp.FieldExp;

public class Column<T> extends FieldExp {

  private final FieldExp table;
  private final String name;
  private final Class<T> type;

  public Column(FieldExp table, String name, Class<T> type) {
    super(field(name), table);
    this.table = table;
    this.name = name;
    this.type = type;
  }

  public FieldExp getTable() {
    return table;
  }

  public String getName() {
    return name;
  }

  public Class<T> getType() {
    return type;
  }
}
