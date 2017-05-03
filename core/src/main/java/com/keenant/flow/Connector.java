package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;

import java.sql.Connection;

/**
 * Provides an opened database connection.
 *
 * The implementation can choose what connection is provided for each call to {@link #acquire()}, examples:
 * <ul>
 *     <li>The same connection each time, until it is disposed</li>
 *     <li>A random connection from a pool of connections</li>
 *     <li>A new connection for each thread</li>
 * </ul>
 */
public interface Connector {
    /**
     * Open a new database connection.
     * @return a new connection
     * @throws DatabaseException if the connection fails
     */
    Connection acquire() throws DatabaseException;

    /**
     * Dispose a connection.
     * @param connection the connection
     */
    void dispose(Connection connection);

    /**
     * Dispose of all connections.
     */
    void disposeAll();
}
