package com.keenant.flow;

public enum Comparator {
  EQUALS("="),
  NOT_EQUAL("<>"),
  LESS("<"),
  LESS_OR_EQUAL("<="),
  GREATER(">"),
  GREATER_OR_EQUAL(">="),
  LIKE("LIKE");

  private final String sql;

  Comparator(String sql) {
    this.sql = sql;
  }

  public String getSql() {
    return sql;
  }
}