package com.keenant.flow.exception;

import java.sql.SQLException;

public class DatasourceException extends RuntimeException {
    public DatasourceException(String msg) {
        super(msg);
    }

    public DatasourceException(SQLException e) {
        super("SQL Error: " + e.getErrorCode(), e);
    }
}
