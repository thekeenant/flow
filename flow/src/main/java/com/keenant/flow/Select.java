package com.keenant.flow;

import com.keenant.flow.exp.functions.ListExp;
import com.keenant.flow.jdbc.FetchConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Select {

  private Exp table;
  private ListExp fields;
  private Filter filter;
  private Exp order;
  private Collection<Exp> joins;

  public Select(Exp table) {
    this.table = table;
  }

  public Select cpy() {
    Select select = new Select(table);
    select.fields = fields; // immutable
    select.filter = filter; // immutable
    return select;
  }

  public Select table(Exp table) {
    this.table = table;
    return this;
  }

  public Select fields(Collection<Exp> fields) {
    this.fields = new ListExp(fields);
    return this;
  }

  public Select fields(Exp... fields) {
    return fields(Arrays.asList(fields));
  }

  public Select where(Filter filter) {
    this.filter = filter;
    return this;
  }

  public Select order(Exp order) {
    this.order = order;
    return this;
  }

  public Select join(Collection<Exp> joins) {
    this.joins = joins;
    return this;
  }

  public Select join(Exp... joins) {
    this.joins = Arrays.asList(joins);
    return this;
  }

  public QueryPart build(SQLDialect dialect) {
    QueryPart tablePart = table.build(dialect);
    QueryPart fieldsPart = fields == null ? Flow.wildcard().build(dialect) : fields.build(dialect);
    QueryPart filterPart = filter == null ? null : filter.build(dialect);
    QueryPart orderPart = order == null ? null : order.build(dialect);

    StringBuilder sql = new StringBuilder();
    List<Object> params = new ArrayList<>();

    sql.append("SELECT ");

    sql.append(fieldsPart.getSql());
    params.addAll(fieldsPart.getParams());

    sql.append(" FROM ");

    sql.append(tablePart.getSql());
    params.addAll(tablePart.getParams());

    if (filterPart != null) {
      sql.append(" WHERE ");

      sql.append(filterPart.getSql());
      params.addAll(filterPart.getParams());
    }

    if (orderPart != null) {
      sql.append(" ORDER BY ");

      sql.append(orderPart.getSql());
      params.addAll(orderPart.getParams());
    }

    if (joins != null) {
      this.joins.stream().map(j -> j.build(dialect)).forEach(joinPart -> {
        sql.append(joinPart.getSql());
        params.addAll(joinPart.getParams());
        sql.append(" ");
      });

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
