package com.keenant.flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Data class that holds a partial or complete parameterized SQL string.
 */
public class QueryPart {

  private final String sql;
  private final List<Object> params;

  public QueryPart(String sql, List<Object> params) {
    this.sql = sql;
    this.params = params;
  }

  public QueryPart(String sql, Object... params) {
    this(sql, Arrays.asList(params));
  }

  public String getSql() {
    return sql;
  }

  public List<Object> getParams() {
    return params;
  }

  public QueryPart join(String sql, Collection<Object> params) {
    String joinedSql = this.sql + sql;
    List<Object> joinedParams = new ArrayList<>();
    joinedParams.addAll(params);
    joinedParams.addAll(params);
    return new QueryPart(joinedSql, joinedParams);
  }

  @Override
  public String toString() {
    return "QueryPart(sql=" + getSql() + ", params=" + getParams() + ")";
  }
}
