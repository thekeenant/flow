package com.keenant.flow.exp;

import com.keenant.flow.AbstractExp;
import com.keenant.flow.QueryPart;
import com.keenant.flow.SQLDialect;
import java.util.Arrays;
import java.util.List;

/**
 * A raw SQL expression.
 */
public class PlainExp extends AbstractExp {

  private final QueryPart part;

  public PlainExp(String sql, List<Object> params) {
    this.part = new QueryPart(sql, params);
  }

  public PlainExp(String sql, Object... params) {
    this(sql, Arrays.asList(params));
  }

  @Override
  public QueryPart build(SQLDialect dialect) {
    return part;
  }
}
