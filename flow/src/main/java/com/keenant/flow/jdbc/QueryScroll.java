package com.keenant.flow.jdbc;

import java.sql.ResultSet;

/**
 * Some drivers support moving back and forth in a result set.
 */
public enum QueryScroll {
  /**
   * {@link ResultSet#FETCH_FORWARD}
   */
  FORWARD_ONLY(ResultSet.TYPE_FORWARD_ONLY),

  /**
   * {@link ResultSet#TYPE_SCROLL_INSENSITIVE}
   */
  INSENSITIVE(ResultSet.TYPE_SCROLL_INSENSITIVE),

  /**
   * {@link ResultSet#TYPE_SCROLL_SENSITIVE}
   */
  SENSITIVE(ResultSet.TYPE_SCROLL_SENSITIVE);

  private int value;

  QueryScroll(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
