package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EagerCursor extends Cursor {
  public EagerCursor(PreparedStatement statement, ResultSet resultSet, Runnable releaser) {
    super(statement, resultSet, releaser);
  }

  public void moveTo(int record) {
    try {
      resultSet.absolute(record);
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

  public EagerCursor move(int record) {
    moveTo(record);
    return this;
  }

  public void moveToFirst() {
    try {
      resultSet.first();
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

  public EagerCursor first() {
    moveToFirst();
    return this;
  }

  public void moveToLast() {
    try {
      resultSet.last();
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

  public EagerCursor last() {
    moveToLast();
    return this;
  }
}