package com.keenant.flow.exp;

import com.keenant.flow.AbstractUnaryExp;
import com.keenant.flow.Exp;
import com.keenant.flow.SQLDialect;

public class JoinExp extends AbstractUnaryExp {
  public JoinExp(Exp child) {
    super(child);
  }

  @Override
  protected String getSqlFormat(SQLDialect dialect) {
    return "JOIN %s";
  }
}
