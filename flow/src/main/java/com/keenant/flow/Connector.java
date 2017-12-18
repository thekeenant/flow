package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;
import java.sql.Connection;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Provides an opened database connection.
 *
 * The implementation can choose what connection is provided for each call to {@link #acquire()},
 * examples:
 * <ul>
 *   <li>The same connection each time, until it is disposed</li>
 *   <li>A random connection from a pool of connections</li>
 *   <li>A new connection for each thread</li>
 * </ul>
 */
public interface Connector {
  /**
   * Construct an anonymous connector. This is handy in Java 8 when implementing interfaces that
   * have multiple methods, since you can now use functional programming!
   *
   * @param acquire supplies a connection
   * @param dispose disposes a connection
   * @param disposeAll disposes all connections
   * @return the connector
   */
  static Connector of(Supplier<Connection> acquire, Consumer<Connection> dispose,
      Runnable disposeAll) {
    return new Connector() {
      @Override
      public Connection acquire() throws DatabaseException {
        return acquire.get();
      }

      @Override
      public void release(Connection connection) {
        dispose.accept(connection);
      }

      @Override
      public void releaseAll() {
        disposeAll.run();
      }
    };
  }

  /**
   * Provide the caller with a connection to the database.
   *
   * @return a connection
   * @throws DatabaseException if the connection fails
   */
  Connection acquire() throws DatabaseException;

  /**
   * Release an acquired connection.
   *
   * @param connection the connection
   */
  void release(Connection connection);

  default Runnable releaser(Connection connection) {
    return () -> release(connection);
  }

  /**
   * Dispose of all connections.
   */
  void releaseAll();
}
