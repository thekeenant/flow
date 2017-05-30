package com.keenant.flow.impl.filter;

import com.keenant.flow.Filter;
import com.keenant.flow.QueryPart;
import com.keenant.flow.SQLDialect;
import com.keenant.flow.impl.QueryPartImpl;

public abstract class AbstractUnaryFilter extends AbstractFilter {
    private final Filter child;

    public AbstractUnaryFilter(Filter child) {
        this.child = child;
    }

    protected abstract String getSqlFormat(SQLDialect dialect);

    @Override
    public QueryPart build(SQLDialect dialect) {
        QueryPart part = child.build(dialect);

        String sql = String.format(getSqlFormat(dialect), part.getSql());

        return new QueryPartImpl(sql, part.getParams());
    }
}
