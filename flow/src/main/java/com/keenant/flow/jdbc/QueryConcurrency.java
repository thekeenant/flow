package com.keenant.flow.jdbc;

import java.sql.ResultSet;

/**
 * Some drivers support "updatable" records as they are selected.
 */
public enum QueryConcurrency {
  READ_ONLY(ResultSet.CONCUR_READ_ONLY),
  UPDATABLE(ResultSet.CONCUR_UPDATABLE);

  private int value;

  QueryConcurrency(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
