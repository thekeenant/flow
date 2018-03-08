package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;
import com.keenant.flow.exp.functions.ListExp;
import com.keenant.flow.jdbc.FetchConfig;
import com.keenant.flow.jdbc.QueryScroll;
import com.keenant.flow.jdbc.QueryType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class DatabaseContext implements AutoCloseable {

  private final SQLDialect dialect;
  private final Connector connector;

  public DatabaseContext(SQLDialect dialect, Connector connector) {
    this.dialect = dialect;
    this.connector = connector;
  }

  public Query prepareUpdate(String sql, Collection<Object> params) {
    try {
      Connection connection = connector.acquire();
      PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      Iterator<?> iterator = params.iterator();
      int i = 1;
      while (iterator.hasNext()) {
        statement.setObject(i, iterator.next());
        i++;
      }

      // Create the query object, passing on the query config to it
      return new Query(statement, QueryType.UPDATE, connector.releaser(connection));
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

  public Query prepareUpdate(String sql, Object... params) {
    return prepareUpdate(sql, Arrays.asList(params));
  }

  public Query prepareUpdate(QueryPart part) {
    return prepareUpdate(part.getSql(), part.getParams());
  }

  public Query prepareFetch(FetchConfig config, String sql, Collection<?> params) {
    try {
      Connection connection = connector.acquire();

      @SuppressWarnings("MagicConstant")
      PreparedStatement statement = connection.prepareStatement(
          sql,
          config.getType().getValue(),
          config.getConcurrency().getValue()
      );

      Iterator<?> iterator = params.iterator();
      int i = 1;
      while (iterator.hasNext()) {
        statement.setObject(i, iterator.next());
        i++;
      }

      // Create the query object, passing on the query config to it
      return new Query(statement, QueryType.FETCH, connector.releaser(connection));
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

  public Query prepareFetch(String sql, Collection<?> params) {
    return prepareFetch(FetchConfig.DEFAULT, sql, params);
  }

  public Query prepareFetch(FetchConfig config, String sql, Object... params) {
    return prepareFetch(config, sql, Arrays.asList(params));
  }

  public Query prepareFetch(String sql, Object... params) {
    return prepareFetch(FetchConfig.DEFAULT, sql, Arrays.asList(params));
  }

  public Query prepareFetch(FetchConfig config, QueryPart part) {
    return prepareFetch(config, part.getSql(), part.getParams());
  }

  public Query prepareFetch(QueryPart part) {
    return prepareFetch(FetchConfig.DEFAULT, part);
  }

  public EagerCursor fetch(String sql, Collection<?> params) {
    if (dialect.supportsScrolling()) {
      FetchConfig config = FetchConfig.builder()
          .type(QueryScroll.INSENSITIVE)
          .build();
      return prepareFetch(config, sql, params).execute().eagerCursor();
    } else {
      FetchConfig config = FetchConfig.builder()
          .type(QueryScroll.FORWARD_ONLY)
          .build();
      return prepareFetch(config, sql, params).execute().safeEagerCursor();
    }
  }

  public EagerCursor fetch(String sql, Object... params) {
    return fetch(sql, Arrays.asList(params));
  }

  public EagerCursor fetch(QueryPart part) {
    return fetch(part.getSql(), part.getParams());
  }

  public Cursor fetchLazy(String sql, Collection<?> params) {
    FetchConfig config = FetchConfig.builder()
        .type(QueryScroll.FORWARD_ONLY)
        .build();
    return prepareFetch(config, sql, params).execute().lazyCursor();
  }

  public Cursor fetchLazy(String sql, Object... params) {
    return fetchLazy(sql, Arrays.asList(params));
  }

  public Cursor fetchLazy(QueryPart part) {
    return fetchLazy(part.getSql(), part.getParams());
  }

  public SelectPrefixScoped select(Exp... fields) {
    return new SelectPrefixScoped(new ListExp(fields), this, dialect);
  }

  public DeleteScoped deleteFrom(Exp table) {
    return new DeleteScoped(table, this, dialect);
  }

  public InsertScoped insertInto(Exp table) {
    return new InsertScoped(table, this, dialect);
  }

  @Override
  public void close() {
    connector.releaseAll();
  }
}
