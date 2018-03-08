package com.keenant.flow;

import com.keenant.flow.exp.functions.ListExp;

public class SelectPrefixScoped {
  private final ListExp fields;
  private final DatabaseContext database;
  private final SQLDialect dialect;

  public SelectPrefixScoped(ListExp fields, DatabaseContext database, SQLDialect dialect) {
    this.fields = fields;
    this.database = database;
    this.dialect = dialect;
  }

  public SelectScoped from(Exp table) {
    return new SelectScoped(table, fields, database, dialect);
  }
}
