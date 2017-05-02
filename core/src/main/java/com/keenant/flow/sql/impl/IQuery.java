package com.keenant.flow.sql.impl;

import com.keenant.flow.exception.DatasourceException;
import com.keenant.flow.sql.Query;
import com.keenant.flow.sql.Result;
import com.keenant.flow.sql.jdbc.QueryConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IQuery implements Query {
    private final PreparedStatement statement;
    private final QueryConfig config;

    public IQuery(PreparedStatement statement, QueryConfig config) {
        this.statement = statement;
        this.config = config;
    }

    @Override
    public Result execute() {
        try {
            ResultSet resultSet;
            ResultSet generated = null;

            switch (config.getMode()) {
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

            return new IResult(statement, resultSet, generated);
        } catch (SQLException e) {
            throw new DatasourceException(e);
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
