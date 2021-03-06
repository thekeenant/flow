package com.keenant.flow;

import com.keenant.flow.exp.AliasExp;
import com.keenant.flow.exp.DistinctExp;
import com.keenant.flow.exp.FieldExp;
import com.keenant.flow.exp.ParamExp;
import com.keenant.flow.exp.PlainExp;
import com.keenant.flow.exp.functions.AbsExp;
import com.keenant.flow.exp.functions.AvgExp;
import com.keenant.flow.exp.functions.CountExp;
import com.keenant.flow.exp.functions.LengthExp;
import com.keenant.flow.exp.functions.ListExp;
import com.keenant.flow.exp.functions.LowerExp;
import com.keenant.flow.exp.functions.MaxExp;
import com.keenant.flow.exp.functions.MinExp;
import com.keenant.flow.exp.functions.OrderExp;
import com.keenant.flow.exp.functions.SumExp;
import com.keenant.flow.exp.functions.UpperExp;
import com.keenant.flow.filter.PlainFilter;
import com.keenant.flow.jdbc.Order;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class Flow {

  private static final Exp WILDCARD = new PlainExp("*");

  public static DefaultConnector connect(String url) {
    return connect(url, null, null);
  }

  public static DefaultConnector connect(String url, String username) {
    return connect(url, username, null);
  }

  public static DefaultConnector connect(String url, String username, String password) {
    return new DefaultConnector(url, username, password);
  }

  public static DatabaseContext database(SQLDialect dialect, Connector connector) {
    return new DatabaseContext(dialect, connector);
  }

  public static DatabaseContext database(SQLDialect dialect, String url) {
    return database(dialect, connect(url));
  }

  public static SelectPrefix select() {
    return select(wildcard());
  }

  public static SelectPrefix select(Exp... fields) {
    return new SelectPrefix(new ListExp(fields));
  }

  public static Insert insertInto(Exp table) {
    return new Insert(table);
  }

  public static QueryPart parameterize(String sql, Object... params) {
    return new QueryPart(sql, params);
  }

  public static QueryPart parameterize(String sql, Collection<?> params) {
    return new QueryPart(sql, params);
  }

  public static <T> Column<T> column(FieldExp table, String name) {
    return new Column<>(table, name);
  }

  public static PlainFilter filter(String sql, Object... params) {
    return new PlainFilter(sql, params);
  }

  public static PlainFilter filter(String sql, List<Object> params) {
    return new PlainFilter(sql, params);
  }

  public static AliasExp alias() {
    String unique = UUID.randomUUID().toString().replace("-", "");
    return alias(unique);
  }

  public static AliasExp alias(String name) {
    return new AliasExp(name);
  }

  public static AbsExp abs(Exp exp) {
    return new AbsExp(exp);
  }

  public static AvgExp avg(Exp exp) {
    return new AvgExp(exp);
  }

  public static CountExp count() {
    return new CountExp(wildcard());
  }

  public static CountExp count(Exp exp) {
    return new CountExp(exp);
  }

  public static DistinctExp distinct(Exp exp) {
    return new DistinctExp(exp);
  }

  public static FieldExp field(String field) {
    return new FieldExp(field);
  }

  public static LengthExp length(Exp exp) {
    return new LengthExp(exp);
  }

  public static ListExp list(Collection<Exp> exps) {
    return new ListExp(exps);
  }

  public static ListExp list(Exp... exps) {
    return new ListExp(Arrays.asList(exps));
  }

  public static LowerExp lower(Exp exp) {
    return new LowerExp(exp);
  }

  public static MaxExp max(Exp exp) {
    return new MaxExp(exp);
  }

  public static MinExp min(Exp exp) {
    return new MinExp(exp);
  }

  public static OrderExp order(Exp exp, Order order) {
    return new OrderExp(exp, order);
  }

  public static OrderExp orderAsc(Exp exp) {
    return new OrderExp(exp, Order.ASC);
  }

  public static OrderExp orderDesc(Exp exp) {
    return new OrderExp(exp, Order.DESC);
  }

  public static ParamExp param(Object object) {
    return new ParamExp(object);
  }

  public static PlainExp plain(String sql) {
    return new PlainExp(sql);
  }

  public static SumExp sum(Exp exp) {
    return new SumExp(exp);
  }

  public static UpperExp upper(Exp exp) {
    return new UpperExp(exp);
  }

  public static Exp wildcard() {
    return WILDCARD;
  }
}
