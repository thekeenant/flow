package com.keenant.flow.impl.filter;

import com.keenant.flow.Filter;
import com.keenant.flow.QueryPart;
import com.keenant.flow.SQLDialect;
import com.keenant.flow.impl.QueryPartImpl;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBinaryFilter extends AbstractFilter {
    private final Filter filter1;
    private final Filter filter2;

    public AbstractBinaryFilter(Filter filter1, Filter filter2) {
        this.filter1 = filter1;
        this.filter2 = filter2;
    }

    protected abstract String getSqlFormat(SQLDialect dialect);

    @Override
    public QueryPart build(SQLDialect dialect) {
        QueryPart part1 = filter1.build(dialect);
        QueryPart part2 = filter2.build(dialect);

        String sql = String.format(getSqlFormat(dialect), part1.getSql(), part2.getSql());
        List<Object> params = new ArrayList<>();
        params.addAll(part1.getParams());
        params.addAll(part2.getParams());
        return new QueryPartImpl(sql, params);
    }
}
