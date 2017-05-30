package com.keenant.flow.impl;

import com.keenant.flow.*;
import com.keenant.flow.impl.exp.ListExp;
import com.keenant.flow.jdbc.FetchConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SelectImpl implements Select {
    private Exp table;
    private boolean distinct;
    private ListExp fields;
    private Filter filter;
    private Exp order;

    public SelectImpl(Exp table) {
        this.table = table;
    }

    @Override
    public Select cpy() {
        SelectImpl select = new SelectImpl(table);
        select.fields = fields; // immutable
        select.filter = filter; // immutable
        return select;
    }

    @Override
    public Select distinct(boolean distinct) {
        this.distinct = distinct;
        return this;
    }

    @Override
    public Select table(Exp table) {
        this.table = table;
        return this;
    }

    @Override
    public Select fields(Collection<Exp> fields) {
        this.fields = new ListExp(fields);
        return this;
    }

    @Override
    public Select fields(Exp... fields) {
        return fields(Arrays.asList(fields));
    }

    @Override
    public Select where(Filter filter) {
        this.filter = filter;
        return this;
    }

    @Override
    public Select order(Exp order) {
        this.order = order;
        return this;
    }

    @Override
    public QueryPart build(SQLDialect dialect) {
        QueryPart tablePart = table.build(dialect);
        QueryPart fieldsPart = fields == null ? Flow.wildcard().build(dialect) : fields.build(dialect);
        QueryPart filterPart = filter == null ? null : filter.build(dialect);
        QueryPart orderPart = order == null ? null : order.build(dialect);

        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<>();


        sql.append("SELECT ");

        if (distinct) {
            sql.append("DISTINCT ");
        }

        sql.append(fieldsPart.getSql());
        params.addAll(fieldsPart.getParams());

        sql.append(" FROM ");

        sql.append(tablePart.getSql());
        params.addAll(tablePart.getParams());

        if (filterPart != null) {
            sql.append(" WHERE ");

            sql.append(filterPart.getSql());
            params.addAll(filterPart.getParams());
        }

        if (orderPart != null) {
            sql.append(" ORDER BY ");

            sql.append(orderPart.getSql());
            params.addAll(orderPart.getParams());
        }

        return new QueryPartImpl(sql.toString(), params);
    }

    private Result execute(DatabaseContext database, SQLDialect dialect, FetchConfig config) {
        return database.prepareFetch(config, build(dialect)).execute();
    }

    @Override
    public EagerCursor fetch(DatabaseContext database, SQLDialect dialect) {
        return database.fetch(build(dialect));
    }

    @Override
    public Cursor fetchLazy(DatabaseContext database, SQLDialect dialect) {
        return database.fetchLazy(build(dialect));
    }
}
