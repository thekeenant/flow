package com.keenant.flow.impl;

import com.keenant.flow.exception.DatabaseException;
import com.keenant.flow.Query;
import com.keenant.flow.Result;
import com.keenant.flow.jdbc.QueryType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryImpl implements Query {
    private final PreparedStatement statement;
    private final QueryType type;

    public QueryImpl(PreparedStatement statement, QueryType type) {
        this.statement = statement;
        this.type = type;
    }

    @Override
    public Result execute() {
        try {
            ResultSet resultSet;
            ResultSet generated = null;

            switch (type) {
                case FETCH:
                    resultSet = statement.executeQuery();
                    break;
                case UPDATE:
                    statement.executeUpdate();
                    resultSet = statement.getResultSet();
                    generated = statement.getGeneratedKeys();
                    break;
                default:
                    throw new IllegalStateException("Invalid query mode");
            }

            return new ResultImpl(statement, resultSet, generated);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void close() {
        try {
            statement.close();
        } catch (SQLException e) {
            // Todo
        }
    }
}
