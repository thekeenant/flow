package com.keenant.flow.sql.impl;

import com.keenant.flow.sql.Cursor;
import com.keenant.flow.sql.EagerCursor;
import com.keenant.flow.sql.Result;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IResult implements Result {
    private final PreparedStatement statement;
    private final ResultSet resultSet;
    private final ResultSet generated;

    public IResult(PreparedStatement statement, ResultSet resultSet, ResultSet generated) {
        this.statement = statement;
        this.resultSet = resultSet;
        this.generated = generated;
    }

    @Override
    public Cursor lazyCursor() {
        return new ICursor(statement, resultSet);
    }

    @Override
    public EagerCursor eagerCursor() {
        return new IEagerCursor(statement, resultSet);
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
        return new ICursor(statement, generated);
    }

    @Override
    public void close() {
        try {
            resultSet.close();
            generated.close();
            statement.close();
        } catch (SQLException e) {
            // Todo
        }
    }
}
