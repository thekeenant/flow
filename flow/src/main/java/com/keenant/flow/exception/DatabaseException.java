package com.keenant.flow.exception;

import java.sql.SQLException;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String msg) {
        super(msg);
    }

    public DatabaseException(SQLException e) {
        super("SQL Error: " + e.getErrorCode(), e);
    }
}
