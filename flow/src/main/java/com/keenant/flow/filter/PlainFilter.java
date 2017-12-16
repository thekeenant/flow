package com.keenant.flow.filter;

import com.keenant.flow.AbstractFilter;
import com.keenant.flow.QueryPart;
import com.keenant.flow.SQLDialect;
import java.util.Arrays;
import java.util.List;

public class PlainFilter extends AbstractFilter {

  private final QueryPart part;

  public PlainFilter(String sql, List<Object> params) {
    this.part = new QueryPart(sql, params);
  }

  public PlainFilter(String sql, Object... params) {
    this(sql, Arrays.asList(params));
  }

  @Override
  public QueryPart build(SQLDialect dialect) {
    return part;
  }
}
