package com.keenant.flow.sql.impl;

import com.keenant.flow.Datasource;
import com.keenant.flow.exception.DatasourceException;
import com.keenant.flow.sql.Query;
import com.keenant.flow.sql.SQLDatabase;
import com.keenant.flow.sql.jdbc.QueryConfig;

import java.sql.*;
import java.util.List;

public class ISQLDatabase implements SQLDatabase {
    private Connection connection;

    public ISQLDatabase() {

    }

    @Override
    public Query prepareQuery(String sql, List<Object> params, QueryConfig config) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql, config.getType().getValue(), config.getConcurrency().getValue());
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            // Create the query object, passing on the query config to it
            return new IQuery(statement, config);
        } catch (SQLException e) {
            throw new DatasourceException(e);
        }
    }

    @Override
    public void open() throws DatasourceException {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
        } catch (SQLException e) {
            throw new DatasourceException(e);
        }
    }

    @Override
    public void close() throws DatasourceException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DatasourceException(e);
        }
        connection = null;
    }
}
