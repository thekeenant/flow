package com.keenant.flow;

import com.keenant.flow.exp.JoinExp;
import com.keenant.flow.exp.functions.ListExp;
import com.keenant.flow.jdbc.FetchConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Select extends AbstractExp {
  private final Exp table;
  private final ListExp fields;
  private Filter filter;
  private Exp order;
  private Collection<JoinExp> joins;
  private ListExp groups;
  private Filter having;

  public Select(Exp table, ListExp fields) {
    this.table = table;
    this.fields = fields;
  }

  public Select cpy() {
    Select select = new Select(table, fields);
    select.filter = filter; // immutable
    select.order = order;
    select.joins = new ArrayList<>(joins);
    select.groups = groups;
    select.having = having;
    return select;
  }

  public Select where(Filter filter) {
    this.filter = this.filter == null ? filter : this.filter.and(filter);
    return this;
  }

  public Select order(Exp order) {
    this.order = this.order == null ? order : new ListExp(this.order, order);
    return this;
  }

  public Select join(Collection<Exp> joins) {
    Collection<JoinExp> joinExps = joins.stream().map(JoinExp::new).collect(Collectors.toList());
    if (this.joins == null)
      this.joins = joinExps;
    else
      this.joins.addAll(joinExps);
    return this;
  }

  public Select join(Exp... joins) {
    return join(Arrays.asList(joins));
  }

  public Select groupBy(Collection<Exp> groups) {
    if (this.groups == null)
      this.groups = new ListExp(groups);
    else
      this.groups = new ListExp(this.groups, new ListExp(groups));
    return this;
  }

  public Select groupBy(Exp... groups) {
    return groupBy(Arrays.asList(groups));
  }

  public Select having(Filter having) {
    this.having = this.having == null ? having : this.having.and(having);
    return this;
  }

  @Override
  public QueryPart build(SQLDialect dialect) {
    QueryPart tablePart = table.build(dialect);
    QueryPart fieldsPart = fields == null ? Flow.wildcard().build(dialect) : fields.build(dialect);
    QueryPart filterPart = filter == null ? null : filter.build(dialect);
    QueryPart groupPart = groups == null ? null : groups.build(dialect);
    QueryPart havingPart = having == null ? null : having.build(dialect);
    QueryPart orderPart = order == null ? null : order.build(dialect);

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
        sql.append(" ");
        sql.append(joinPart.getSql());
        params.addAll(joinPart.getParams());
      });
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
