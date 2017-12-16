package com.keenant.flow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Result implements AutoCloseable {

  private final PreparedStatement statement;
  private final ResultSet resultSet;
  private final ResultSet generated;

  public Result(PreparedStatement statement, ResultSet resultSet, ResultSet generated) {
    this.statement = statement;
    this.resultSet = resultSet;
    this.generated = generated;
  }

  public Cursor lazyCursor() {
    return new Cursor(statement, resultSet);
  }

  public EagerCursor eagerCursor() {
    return new EagerCursor(statement, resultSet);
  }

  public EagerCursor safeEagerCursor() {
    SafeEagerCursor cursor = new SafeEagerCursor(statement, resultSet);
    cursor.populateAndClose();
    return cursor;
  }

  public Cursor generatedCursor() throws IllegalStateException {
    if (generated == null) {
      throw new IllegalStateException("No generated records/fields");
    }
    return new Cursor(statement, generated);
  }

  @Override
  public void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }
      if (generated != null) {
        generated.close();
      }
      statement.close();
    } catch (SQLException e) {
      // Todo
    }
  }
}
