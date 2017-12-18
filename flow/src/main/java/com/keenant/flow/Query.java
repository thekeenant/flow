package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;
import com.keenant.flow.jdbc.QueryType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query implements AutoCloseable {
  private final PreparedStatement statement;
  private final QueryType type;
  private final Runnable releaser;

  public Query(PreparedStatement statement, QueryType type, Runnable releaser) {
    this.statement = statement;
    this.type = type;
    this.releaser = releaser;
  }

  /**
   * Execute this query on the database.
   *
   * @return the result of execution
   */
  public Result execute() {
    try {
      ResultSet resultSet;
      ResultSet generated = null;

      switch (type) {
        case FETCH:
          resultSet = statement.executeQuery();
          break;
        case UPDATE:
          statement.executeUpdate();
          resultSet = statement.getResultSet();
          generated = statement.getGeneratedKeys();
          break;
        default:
          throw new IllegalStateException("Invalid query mode");
      }

      return new Result(statement, resultSet, generated, releaser);
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

  /**
   * Execute this query on the database.
   *
   * @return the result of execution
   */
  public void executeAndClose() {
    Result result = execute();
    result.close();
  }

  @Override
  public void close() {
    try {
      statement.close();
      releaser.run();
    } catch (SQLException e) {
      // Todo
    }
  }
}
