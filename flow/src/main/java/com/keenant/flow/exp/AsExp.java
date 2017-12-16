package com.keenant.flow.exp;

import com.keenant.flow.AbstractExp;
import com.keenant.flow.Exp;
import com.keenant.flow.QueryPart;
import com.keenant.flow.SQLDialect;
import java.util.ArrayList;
import java.util.List;

public class AsExp extends AbstractExp {

  private final Exp exp;
  private final Exp as;

  public AsExp(Exp exp, Exp as) {
    this.exp = exp;
    this.as = as;
  }

  @Override
  public QueryPart build(SQLDialect dialect) {
    String sql = "";
    List<Object> params = new ArrayList<>();

    QueryPart expPart = exp.build(dialect);

    sql += expPart.getSql();
    params.addAll(expPart.getParams());

    if (as != null) {
      QueryPart asPart = as.build(dialect);

      sql += " AS " + asPart.getSql();
      params.addAll(asPart.getParams());
    }

    return new QueryPart(sql, params);
  }
}
