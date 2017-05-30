package com.keenant.flow.impl.filter;

import com.keenant.flow.QueryPart;
import com.keenant.flow.SQLDialect;
import com.keenant.flow.impl.QueryPartImpl;

import java.util.Arrays;
import java.util.List;

public class PlainFilter extends AbstractFilter {
    private final QueryPart part;

    public PlainFilter(String sql, List<Object> params) {
        this.part = new QueryPartImpl(sql, params);
    }

    public PlainFilter(String sql, Object... params) {
        this(sql, Arrays.asList(params));
    }

    @Override
    public QueryPart build(SQLDialect dialect) {
        return part;
    }
}
