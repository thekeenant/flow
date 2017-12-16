package com.keenant.flow.filter;

import com.keenant.flow.AbstractFilter;
import com.keenant.flow.Exp;
import com.keenant.flow.QueryPart;
import com.keenant.flow.SQLDialect;

/**
 * A filter that is simply an expression.
 */
public class ExpFilter extends AbstractFilter {

  private final Exp exp;

  public ExpFilter(Exp exp) {
    this.exp = exp;
  }

  @Override
  public QueryPart build(SQLDialect dialect) {
    return exp.build(dialect);
  }
}
