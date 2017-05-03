package com.keenant.flow.impl;

import com.keenant.flow.*;
import com.keenant.flow.jdbc.QueryConfig;
import com.keenant.flow.jdbc.QueryMode;

import java.util.ArrayList;
import java.util.List;

public class IInsertSelect implements InsertSelect {
    private Exp table;
    private final Select select;

    public IInsertSelect(Exp table, Select select) {
        this.table = table;
        this.select = select;
    }

    @Override
    public InsertSelect cpy() {
        return new IInsertSelect(table, select.cpy());
    }

    @Override
    public InsertSelect table(Exp table) {
        this.table = table;
        return this;
    }

    @Override
    public QueryPart build(SQLDialect dialect) {
        QueryPart tablePart = table.build(dialect);
        QueryPart selectPart = select.build(dialect);

        String sql = "INSERT INTO ";
        List<Object> params = new ArrayList<>();

        sql += tablePart.getSql();
        params.addAll(tablePart.getParams());

        sql += " ";

        sql += selectPart.getSql();
        params.addAll(selectPart.getParams());

        return new IQueryPart(sql, params);
    }

    @Override
    public void execute(DatabaseContext database, SQLDialect dialect) {
        QueryPart part = build(dialect);
        QueryConfig config = QueryConfig.builder(QueryMode.UPDATE).build();

        Query query = database.prepareQuery(part, config);
        query.execute();
    }
}
