package com.keenant.flow;

import static com.keenant.flow.Flow.field;

import com.keenant.flow.exp.FieldExp;

public class Column<T> extends FieldExp {
  private final FieldExp table;
  private final String name;

  Column(FieldExp table, String name) {
    super(field(name), table);
    this.table = table;
    this.name = name;
  }

  public FieldExp getTable() {
    return table;
  }

  public String getName() {
    return name;
  }
}
