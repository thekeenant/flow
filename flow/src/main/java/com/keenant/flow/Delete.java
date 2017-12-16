package com.keenant.flow;

import com.keenant.flow.jdbc.FetchConfig;
import java.util.ArrayList;
import java.util.List;

public class Delete {

  private Exp table;
  private Filter filter;

  public Delete(Exp table) {
    this.table = table;
  }

  public Delete cpy() {
    Delete select = new Delete(table);
    select.filter = filter; // immutable
    return select;
  }

  public Delete table(Exp table) {
    this.table = table;
    return this;
  }

  public Delete where(Filter filter) {
    this.filter = filter;
    return this;
  }

  public QueryPart build(SQLDialect dialect) {
    QueryPart tablePart = table.build(dialect);
    QueryPart filterPart = filter == null ? null : filter.build(dialect);

    StringBuilder sql = new StringBuilder();
    List<Object> params = new ArrayList<>();

    sql.append("DELETE ");

    sql.append(" FROM ");

    sql.append(tablePart.getSql());
    params.addAll(tablePart.getParams());

    if (filterPart != null) {
      sql.append(" WHERE ");

      sql.append(filterPart.getSql());
      params.addAll(filterPart.getParams());
    }

    return new QueryPart(sql.toString(), params);
  }

  private Result execute(DatabaseContext database, SQLDialect dialect, FetchConfig config) {
    return database.prepareFetch(config, build(dialect)).execute();
  }

  public EagerCursor fetch(DatabaseContext database, SQLDialect dialect) {
    return database.fetch(build(dialect));
  }

  public Cursor fetchLazy(DatabaseContext database, SQLDialect dialect) {
    return database.fetchLazy(build(dialect));
  }
}
