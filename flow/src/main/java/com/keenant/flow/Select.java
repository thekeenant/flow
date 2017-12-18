package com.keenant.flow;

import com.keenant.flow.exp.functions.ListExp;
import com.keenant.flow.jdbc.FetchConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Select extends AbstractExp {
  private Exp table;
  private ListExp fields;
  private Filter filter;
  private Exp order;
  private Collection<Exp> joins;
  private ListExp groups;
  private Filter having;

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
    return join(Arrays.asList(joins));
  }

  public Select groupBy(Collection<Exp> groups) {
    this.groups = new ListExp(groups);
    return this;
  }

  public Select groupBy(Exp... groups) {
    return groupBy(Arrays.asList(groups));
  }

  public Select having(Filter having) {
    this.having = having;
    return this;
  }

  public QueryPart build(SQLDialect dialect) {
    QueryPart tablePart = table.build(dialect);
    QueryPart fieldsPart = fields == null ? Flow.wildcard().build(dialect) : fields.build(dialect);
    QueryPart filterPart = filter == null ? null : filter.build(dialect);
    QueryPart groupPart = groups == null ? null : groups.build(dialect);
    QueryPart orderPart = order == null ? null : order.build(dialect);
    QueryPart havingPart = having == null ? null : having.build(dialect);

    StringBuilder sql = new StringBuilder();
    List<Object> params = new ArrayList<>();

    sql.append("SELECT ");

    sql.append(fieldsPart.getSql());
    params.addAll(fieldsPart.getParams());

    sql.append(" FROM ");

    sql.append(tablePart.getSql());
    params.addAll(tablePart.getParams());

    if (joins != null) {
      joins.stream().map(j -> j.build(dialect)).forEach(joinPart -> {
        sql.append(" JOIN ");
        sql.append(joinPart.getSql());
        params.addAll(joinPart.getParams());
        sql.append(" ");
      });
      sql.deleteCharAt(sql.length() - 1);
    }

    if (filterPart != null) {
      sql.append(" WHERE ");

      sql.append(filterPart.getSql());
      params.addAll(filterPart.getParams());
    }

    if (groupPart != null) {
      sql.append(" GROUP BY ");
      sql.append(groupPart.getSql());
      params.addAll(groupPart.getParams());
    }

    if (havingPart != null) {
      sql.append(" HAVING ");
      sql.append(havingPart.getSql());
      params.addAll(havingPart.getParams());
    }

    if (orderPart != null) {
      sql.append(" ORDER BY ");

      sql.append(orderPart.getSql());
      params.addAll(orderPart.getParams());
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
