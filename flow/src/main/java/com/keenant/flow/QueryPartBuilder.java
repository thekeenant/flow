package com.keenant.flow;

/**
 * Something that can construct a part of a parameterized query.
 */
public interface QueryPartBuilder {

  /**
   * Construct the SQL query part
   *
   * @param dialect the SQL dialect to use
   * @return the SQL query component
   */
  QueryPart build(SQLDialect dialect);
}
