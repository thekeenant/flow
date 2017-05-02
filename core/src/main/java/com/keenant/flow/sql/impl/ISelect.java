package com.keenant.flow.sql.impl;

import com.keenant.flow.sql.*;
import com.keenant.flow.sql.jdbc.QueryConfig;
import com.keenant.flow.sql.jdbc.QueryMode;
import com.keenant.flow.sql.jdbc.QueryType;

import java.util.ArrayList;
import java.util.List;

public class ISelect implements Select {
    private Exp table;
    private Filter filter;

    public ISelect(Exp table) {
        this.table = table;
    }

    @Override
    public Select where(Filter filter) {
        this.filter = filter;
        return this;
    }

    @Override
    public QueryPart build(SQLDialect dialect) {
        QueryPart tablePart = table.build(dialect);
        QueryPart filterPart = filter == null ? null : filter.build(dialect);

        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<>();

        sql.append("SELECT * FROM ");

        sql.append(tablePart.getSql());
        params.addAll(tablePart.getParams());

        if (filterPart != null) {
            sql.append(" WHERE ");

            sql.append(filterPart.getSql());
            params.addAll(filterPart.getParams());
        }

        return new IQueryPart(sql.toString(), params);
    }

    private Result execute(SQLDatabase database, QueryConfig config) {
        return database.prepareQuery(build(database.getDialect()), config).execute();
    }

    @Override
    public EagerCursor fetch(SQLDatabase database) {
        QueryConfig config = QueryConfig.builder(QueryMode.FETCH)
                .type(QueryType.SCROLL_INSENSITIVE)
                .build();

        return execute(database, config).eagerCursor();
    }

    @Override
    public Cursor fetchLazy(SQLDatabase database) {
        QueryConfig config = QueryConfig.builder(QueryMode.FETCH)
                .type(QueryType.FORWARD_ONLY)
                .build();

        return execute(database, config).lazyCursor();
    }
}
