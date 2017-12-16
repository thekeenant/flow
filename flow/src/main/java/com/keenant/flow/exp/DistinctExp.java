package com.keenant.flow.exp;

import com.keenant.flow.AbstractUnaryExp;
import com.keenant.flow.Exp;
import com.keenant.flow.SQLDialect;

public class DistinctExp extends AbstractUnaryExp {

  public DistinctExp(Exp child) {
    super(child);
  }

  @Override
  protected String getSqlFormat(SQLDialect dialect) {
    return "DISTINCT %s";
  }
}
