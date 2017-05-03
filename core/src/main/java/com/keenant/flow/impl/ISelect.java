package com.keenant.flow.impl;

import com.keenant.flow.*;
import com.keenant.flow.impl.exp.WildcardExp;
import com.keenant.flow.jdbc.QueryConfig;
import com.keenant.flow.jdbc.QueryMode;
import com.keenant.flow.jdbc.QueryType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ISelect implements Select {
    private Exp table;
    private FieldList fields;
    private Filter filter;
    private Exp order;

    public ISelect(Exp table) {
        this.table = table;
    }

    @Override
    public Select cpy() {
        ISelect select = new ISelect(table);
        select.fields = fields; // immutable
        select.filter = filter; // immutable
        return select;
    }

    @Override
    public Select table(Exp table) {
        this.table = table;
        return this;
    }

    @Override
    public Select fields(Collection<Exp> fields) {
        this.fields = new FieldList(fields);
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
        QueryPart fieldsPart = fields == null ? new WildcardExp().build(dialect) : fields.build(dialect);
        QueryPart filterPart = filter == null ? null : filter.build(dialect);
        QueryPart orderPart = order == null ? null : order.build(dialect);

        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<>();


        sql.append("SELECT ");

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

        return new IQueryPart(sql.toString(), params);
    }

    private Result execute(DatabaseContext database, SQLDialect dialect, QueryConfig config) {
        return database.prepareQuery(build(dialect), config).execute();
    }

    @Override
    public EagerCursor fetch(DatabaseContext database, SQLDialect dialect) {
        if (dialect.supportsScrolling()) {
            QueryConfig config = QueryConfig.builder(QueryMode.FETCH)
                    .type(QueryType.SCROLL_INSENSITIVE)
                    .build();
            return execute(database, dialect, config).eagerCursor();
        }
        else {
            QueryConfig config = QueryConfig.builder(QueryMode.FETCH)
                    .type(QueryType.FORWARD_ONLY)
                    .build();
            return execute(database, dialect, config).safeEagerCursor();
        }
    }

    @Override
    public Cursor fetchLazy(DatabaseContext database, SQLDialect dialect) {
        QueryConfig config = QueryConfig.builder(QueryMode.FETCH)
                .type(QueryType.FORWARD_ONLY)
                .build();

        return execute(database, dialect, config).lazyCursor();
    }
}
