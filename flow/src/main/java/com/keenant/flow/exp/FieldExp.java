package com.keenant.flow.exp;

import com.keenant.flow.AbstractExp;
import com.keenant.flow.Exp;
import com.keenant.flow.QueryPart;
import com.keenant.flow.SQLDialect;
import java.util.ArrayList;
import java.util.List;

public class FieldExp extends AbstractExp {

  private final Exp field;
  private final Exp qualifier;

  public FieldExp(String field) {
    this(new PlainExp(field));
  }

  public FieldExp(Exp field) {
    this(field, null);
  }

  public FieldExp(Exp field, Exp qualifier) {
    this.field = field;
    this.qualifier = qualifier;
  }

  /**
   * Return the depth of this field's qualification.
   *
   * db.table.column -> column has qualifier count of 2
   * table is 1
   * db is 0
   *
   * @return the qualifier count of this field
   */
  public int getQualifierCount() {
    int count = 0;
    Exp curr = qualifier;
    while (curr != null) {
      count++;
      if (curr instanceof FieldExp) {
        FieldExp field = (FieldExp) curr;
        curr = field.qualifier;
      } else {
        curr = null;
      }
    }
    return count;
  }

  @Override
  public QueryPart build(SQLDialect dialect) {
    String sql = "";
    List<Object> params = new ArrayList<>();

    QueryPart fieldPart = field.build(dialect);
    sql += fieldPart.getSql();
    params.addAll(fieldPart.getParams());

    if (qualifier != null) {
      QueryPart qualifierPart = qualifier.build(dialect);
      sql = qualifierPart.getSql() + "." + sql;
      params.addAll(qualifierPart.getParams());
    }

    return new QueryPart(sql, params);
  }
}
