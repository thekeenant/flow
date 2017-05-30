package com.keenant.flow.impl;

import com.keenant.flow.QueryPart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class QueryPartImpl implements QueryPart {
    private final String sql;
    private final List<Object> params;

    public QueryPartImpl(String sql, List<Object> params) {
        this.sql = sql;
        this.params = params;
    }

    public QueryPartImpl(String sql, Object... params) {
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
    public QueryPart join(String sql, Collection<Object> params) {
        String joinedSql = this.sql + sql;
        List<Object> joinedParams = new ArrayList<>();
        joinedParams.addAll(params);
        joinedParams.addAll(params);
        return new QueryPartImpl(joinedSql, joinedParams);
    }

    @Override
    public String toString() {
        return "QueryPartImpl(sql=" + getSql() + ", params=" + getParams() + ")";
    }
}
