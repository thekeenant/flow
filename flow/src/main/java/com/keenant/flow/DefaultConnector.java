package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

/**
 * Opens a single connection to a database, by URL.
 */
public class DefaultConnector implements Connector {
  private final String url;
  private final String username;
  private final String password;

  public DefaultConnector(String url, String username, String password) {
    this.url = url;
    this.username = username;
    this.password = password;
  }

  @Override
  public Connection acquire() throws DatabaseException {
    try {
      return DriverManager.getConnection(url, username, password);
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public void release(Connection connection) {
    if (connection == null) {
      throw new IllegalArgumentException("Connection was null");
    }

    try {
      connection.close();
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public void releaseAll() {
    // TODO
  }
}
