
package com.keenant.flow.sql.impl;

import com.keenant.flow.exception.DatasourceException;
import com.keenant.flow.sql.EagerCursor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IEagerCursor extends ICursor implements EagerCursor {
    public IEagerCursor(PreparedStatement statement, ResultSet resultSet) {
        super(statement, resultSet);
    }

    public void moveTo(int record) {
        try {
            getResultSet().absolute(record);
        } catch (SQLException e) {
            throw new DatasourceException(e);
        }
    }

    public IEagerCursor move(int record) {
        moveTo(record);
        return this;
    }

    public void moveToFirst() {
        try {
            getResultSet().first();
        } catch (SQLException e) {
            throw new DatasourceException(e);
        }
    }

    public IEagerCursor first() {
        moveToFirst();
        return this;
    }

    public void moveToLast() {
        try {
            getResultSet().last();
        } catch (SQLException e) {
            throw new DatasourceException(e);
        }
    }

    public IEagerCursor last() {
        moveToLast();
        return this;
    }

    @Override
    protected void ensureValid() {
        // Always valid
    }
}