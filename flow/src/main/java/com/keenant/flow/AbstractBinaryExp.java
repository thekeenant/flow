package com.keenant.flow;

import java.util.ArrayList;
import java.util.List;

/**
 * An expression that takes two parameters.
 */
public abstract class AbstractBinaryExp extends AbstractExp {

  private final Exp child1;
  private final Exp child2;

  public AbstractBinaryExp(Exp child1, Exp child2) {
    this.child1 = child1;
    this.child2 = child2;
  }

  protected abstract String getSqlFormat(SQLDialect dialect);

  @Override
  public QueryPart build(SQLDialect dialect) {
    QueryPart child1Part = child1.build(dialect);
    QueryPart child2Part = child2.build(dialect);

    String sql = String.format(getSqlFormat(dialect), child1Part.getSql(), child2Part.getSql());

    List<Object> params = new ArrayList<>();
    params.addAll(child1Part.getParams());
    params.addAll(child2Part.getParams());

    return new QueryPart(sql, params);
  }
}
