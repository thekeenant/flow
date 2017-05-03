package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;

import java.sql.Connection;

/**
 * Provides an opened database connection.
 */
@FunctionalInterface
public interface Connector {
    /**
     * Open a new database connection.
     * @return a new connection
     * @throws DatabaseException if the connection fails
     */
    Connection connect() throws DatabaseException;
}
