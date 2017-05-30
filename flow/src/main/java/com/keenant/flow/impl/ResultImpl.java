package com.keenant.flow.impl;

import com.keenant.flow.Cursor;
import com.keenant.flow.EagerCursor;
import com.keenant.flow.Result;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultImpl implements Result {
    private final PreparedStatement statement;
    private final ResultSet resultSet;
    private final ResultSet generated;

    public ResultImpl(PreparedStatement statement, ResultSet resultSet, ResultSet generated) {
        this.statement = statement;
        this.resultSet = resultSet;
        this.generated = generated;
    }

    @Override
    public Cursor lazyCursor() {
        return new CursorImpl(statement, resultSet);
    }

    @Override
    public EagerCursor eagerCursor() {
        return new EagerCursorImpl(statement, resultSet);
    }

    @Override
    public EagerCursor safeEagerCursor() {
        SafeEagerCursor cursor = new SafeEagerCursor(statement, resultSet);
        cursor.populateAndClose();
        return cursor;
    }

    @Override
    public Cursor generatedCursor() throws IllegalStateException {
        if (generated == null)
            throw new IllegalStateException("No generated records/fields");
        return new CursorImpl(statement, generated);
    }

    @Override
    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (generated != null) {
                generated.close();
            }
            statement.close();
        } catch (SQLException e) {
            // Todo
        }
    }
}
