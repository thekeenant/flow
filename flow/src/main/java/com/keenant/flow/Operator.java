package com.keenant.flow;

public enum Operator {
  ADD("+"),
  SUBTRACT("-"),
  DIVIDE("/"),
  MULTIPLY("*"),
  MODULO("%");

  private final String sql;

  Operator(String sql) {
    this.sql = sql;
  }

  public String getSql() {
    return sql;
  }
}