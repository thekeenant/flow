package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;
import com.keenant.flow.exp.functions.ListExp;
import java.util.Collection;
import java.util.stream.Stream;

public class SelectScoped implements QueryPartBuilder {
  private final Select select;
  private final DatabaseContext database;
  private final SQLDialect dialect;

  private SelectScoped(Select select, DatabaseContext database, SQLDialect dialect) {
    this.select = select;
    this.database = database;
    this.dialect = dialect;
  }

  public SelectScoped(Exp table, ListExp fields, DatabaseContext database, SQLDialect dialect) {
    this(new Select(table, fields), database, dialect);
  }

  public DatabaseContext getDatabase() {
    return database;
  }

  public SQLDialect getDialect() {
    return dialect;
  }

  public QueryPart build() {
    return select.build(dialect);
  }

  public EagerCursor fetch() {
    return select.fetch(database, dialect);
  }

  public Stream<Cursor> stream() {
    return fetch().stream();
  }

  public Cursor fetchLazy() throws DatabaseException {
    return select.fetchLazy(database, dialect);
  }

  public Stream<Cursor> streamLazy() {
    return fetchLazy().stream();
  }

  public SelectScoped cpy() {
    return new SelectScoped(select.cpy(), database, dialect);
  }

  public SelectScoped where(Filter filter) {
    select.where(filter);
    return this;
  }

  public SelectScoped order(Exp order) {
    select.order(order);
    return this;
  }

  public SelectScoped join(Collection<Exp> joins) {
    select.join(joins);
    return this;
  }

  public SelectScoped join(Exp... joins) {
    select.join(joins);
    return this;
  }

  public SelectScoped groupBy(Collection<Exp> groups) {
    select.groupBy(groups);
    return this;
  }

  public SelectScoped groupBy(Exp... groups) {
    select.groupBy(groups);
    return this;
  }

  public SelectScoped having(Filter having) {
    select.having(having);
    return this;
  }

  public EagerCursor fetch(DatabaseContext database, SQLDialect dialect) {
    return select.fetch(database, dialect);
  }

  public Stream<Cursor> stream(DatabaseContext database, SQLDialect dialect) {
    return select.stream(database, dialect);
  }

  public Cursor fetchLazy(DatabaseContext database, SQLDialect dialect) throws DatabaseException {
    return select.fetchLazy(database, dialect);
  }

  public Stream<Cursor> streamLazy(DatabaseContext database, SQLDialect dialect) {
    return select.streamLazy(database, dialect);
  }

  @Override
  public QueryPart build(SQLDialect dialect) {
    return select.build(dialect);
  }
}
