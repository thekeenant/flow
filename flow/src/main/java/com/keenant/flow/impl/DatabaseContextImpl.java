package com.keenant.flow.impl;

import com.keenant.flow.*;
import com.keenant.flow.exception.DatabaseException;
import com.keenant.flow.jdbc.FetchConfig;
import com.keenant.flow.jdbc.QueryScroll;
import com.keenant.flow.jdbc.QueryType;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;

public class DatabaseContextImpl implements DatabaseContext {
    private final SQLDialect dialect;
    private final Connector connector;

    public DatabaseContextImpl(SQLDialect dialect, Connector connector) {
        this.dialect = dialect;
        this.connector = connector;
    }

    @Override
    public Query prepareUpdate(String sql, Collection<?> params) {
        try {
            PreparedStatement statement = connector.acquire().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            Iterator<?> iterator = params.iterator();
            int i = 1;
            while (iterator.hasNext()) {
                statement.setObject(i, iterator.next());
                i++;
            }

            // Create the query object, passing on the query config to it
            return new QueryImpl(statement, QueryType.UPDATE);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Query prepareFetch(FetchConfig config, String sql, Collection<?> params) {
        try {
            PreparedStatement statement = connector.acquire().prepareStatement(sql, config.getType().getValue(), config.getConcurrency().getValue());

            Iterator<?> iterator = params.iterator();
            int i = 1;
            while (iterator.hasNext()) {
                statement.setObject(i, iterator.next());
                i++;
            }

            // Create the query object, passing on the query config to it
            return new QueryImpl(statement, QueryType.FETCH);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public EagerCursor fetch(String sql, Collection<?> params) {
        if (dialect.supportsScrolling()) {
            FetchConfig config = FetchConfig.builder()
                    .type(QueryScroll.INSENSITIVE)
                    .build();
            return prepareFetch(config, sql, params).execute().eagerCursor();
        }
        else {
            FetchConfig config = FetchConfig.builder()
                    .type(QueryScroll.FORWARD_ONLY)
                    .build();
            return prepareFetch(config, sql, params).execute().safeEagerCursor();
        }
    }

    @Override
    public Cursor fetchLazy(String sql, Collection<?> params) {
        FetchConfig config = FetchConfig.builder()
                .type(QueryScroll.FORWARD_ONLY)
                .build();
        return prepareFetch(config, sql, params).execute().lazyCursor();
    }

    @Override
    public SelectScoped selectFrom(Exp table) {
        return new SelectScopedImpl(table, this, dialect);
    }

    @Override
    public InsertScoped insertInto(Exp table) {
        return new InsertScopedImpl(table, this, dialect);
    }

    @Override
    public void close() {
        connector.disposeAll();
    }
}
