package com.keenant.flow.impl;

import com.keenant.flow.Connector;
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
    private Connection current;

    public DefaultConnector(String url) {
        this.url = url;
    }

    private IllegalStateException closedException() {
        return new IllegalStateException("Connection not made");
    }

    public Optional<Connection> getConnection() {
        return Optional.ofNullable(current);
    }

    public void setAutoCommit(boolean auto) throws IllegalStateException, DatabaseException {
        try {
            getConnection().orElseThrow(this::closedException).setAutoCommit(auto);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public void commit() throws IllegalStateException, DatabaseException {
        try {
            getConnection().orElseThrow(this::closedException).commit();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public void rollback() throws IllegalStateException, DatabaseException {
        try {
            getConnection().orElseThrow(this::closedException).rollback();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Connection acquire() throws DatabaseException {
        try {
            return current = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void dispose(Connection connection) {
        if (Objects.equals(current, connection)) {
            current = null;
        }
    }

    @Override
    public void disposeAll() {
        getConnection().ifPresent(conn -> {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DatabaseException(e);
            }
        });
    }
}
