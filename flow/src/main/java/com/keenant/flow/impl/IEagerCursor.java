
package com.keenant.flow.impl;

import com.keenant.flow.exception.DatabaseException;
import com.keenant.flow.EagerCursor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IEagerCursor extends ICursor implements EagerCursor {
    private ResultSet resultSet;

    public IEagerCursor(PreparedStatement statement, ResultSet resultSet) {
        super(statement, resultSet);
        this.resultSet = resultSet;
    }

    public void moveTo(int record) {
        try {
            resultSet.absolute(record);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public IEagerCursor move(int record) {
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

    public IEagerCursor first() {
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

    public IEagerCursor last() {
        moveToLast();
        return this;
    }
}