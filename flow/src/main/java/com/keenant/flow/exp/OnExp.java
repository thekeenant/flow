package com.keenant.flow.exp;

import com.keenant.flow.AbstractExp;
import com.keenant.flow.Exp;
import com.keenant.flow.Filter;
import com.keenant.flow.QueryPart;
import com.keenant.flow.SQLDialect;
import java.util.ArrayList;
import java.util.List;

public class OnExp extends AbstractExp {

  private final Exp table;
  private final Filter on;

  public OnExp(Exp table, Filter on) {
    this.table = table;
    this.on = on;
  }

  @Override
  public QueryPart build(SQLDialect dialect) {
    String sql = "";
    List<Object> params = new ArrayList<>();

    QueryPart tablePart = table.build(dialect);

    sql += tablePart.getSql();
    params.addAll(tablePart.getParams());

    if (on != null) {
      QueryPart onPart = on.build(dialect);

      sql += " ON " + onPart.getSql();
      params.addAll(onPart.getParams());
    }
    
    return new QueryPart(sql, params);
  }
}
