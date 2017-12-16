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

  private Connection current;

  public DefaultConnector(String url, String username, String password) {
    this.url = url;
    this.username = username;
    this.password = password;
  }

  private Optional<Connection> getConnection() {
    return Optional.ofNullable(current);
  }

  public void setAutoCommit(boolean auto) throws DatabaseException {
    try {
      acquire().setAutoCommit(auto);
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

  public void commit() throws DatabaseException {
    try {
      acquire().commit();
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

  public void rollback() throws DatabaseException {
    try {
      acquire().rollback();
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public Connection acquire() throws DatabaseException {
    try {
      if (current == null) {
        current = DriverManager.getConnection(url, username, password);
      }
      return current;
    } catch (SQLException e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public void dispose(Connection connection) {
    if (connection == null) {
      throw new IllegalArgumentException("Connection was null");
    }

    if (Objects.equals(current, connection)) {
      try {
        connection.close();
      } catch (SQLException e) {
        throw new DatabaseException(e);
      }
      current = null;
    }
  }

  @Override
  public void disposeAll() {
    getConnection().ifPresent(this::dispose);
  }
}
