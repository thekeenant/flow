package com.keenant.flow.exp.functions;

import com.keenant.flow.AbstractUnaryExp;
import com.keenant.flow.Exp;
import com.keenant.flow.SQLDialect;

public class LengthExp extends AbstractUnaryExp {

  public LengthExp(Exp child) {
    super(child);
  }

  @Override
  protected String getSqlFormat(SQLDialect dialect) {
    return "LENGTH(%s)";
  }
}
