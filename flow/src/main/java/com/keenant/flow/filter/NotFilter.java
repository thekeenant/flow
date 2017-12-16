package com.keenant.flow.filter;

import com.keenant.flow.AbstractUnaryFilter;
import com.keenant.flow.Filter;
import com.keenant.flow.SQLDialect;

public class NotFilter extends AbstractUnaryFilter {

  public NotFilter(Filter child) {
    super(child);
  }

  @Override
  protected String getSqlFormat(SQLDialect dialect) {
    return "NOT (%s)";
  }
}
