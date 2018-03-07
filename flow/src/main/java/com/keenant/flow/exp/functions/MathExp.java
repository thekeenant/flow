package com.keenant.flow.exp.functions;

import com.keenant.flow.*;

public class MathExp extends AbstractBinaryExp {
  private final Operator operator;

  public MathExp(Exp child1, Exp child2, Operator operator) {
    super(child1, child2);
    this.operator = operator;
  }

  @Override
  protected String getSqlFormat(SQLDialect dialect) {
    return "(%s) " + operator.getSql() + " (%s)";
  }
}
