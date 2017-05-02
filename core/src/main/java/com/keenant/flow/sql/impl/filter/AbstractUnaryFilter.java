package com.keenant.flow.sql.impl.filter;

import com.keenant.flow.sql.Filter;
import com.keenant.flow.sql.QueryPart;
import com.keenant.flow.sql.SQLDialect;
import com.keenant.flow.sql.impl.AbstractFilter;
import com.keenant.flow.sql.impl.IQueryPart;

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

        return new IQueryPart(sql, part.getParams());
    }
}
