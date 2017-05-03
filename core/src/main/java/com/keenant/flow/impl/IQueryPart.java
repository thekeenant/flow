package com.keenant.flow.impl;

import com.keenant.flow.QueryPart;

import java.util.Arrays;
import java.util.List;

public class IQueryPart implements QueryPart {
    private final String sql;
    private final List<Object> params;

    public IQueryPart(String sql, List<Object> params) {
        this.sql = sql;
        this.params = params;
    }

    public IQueryPart(String sql, Object... params) {
        this(sql, Arrays.asList(params));
    }

    @Override
    public String getSql() {
        return sql;
    }

    @Override
    public List<Object> getParams() {
        return params;
    }

    @Override
    public String toString() {
        return "IQueryPart(sql=" + getSql() + ", params=" + getParams() + ")";
    }
}
