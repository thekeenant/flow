package com.keenant.flow.impl;

import com.keenant.flow.QueryPart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IQueryPart implements QueryPart {
    public static final IQueryPart EMPTY = new IQueryPart("", Collections.emptyList());

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
    public QueryPart join(String sql, List<Object> params) {
        String joinedSql = this.sql + sql;
        List<Object> joinedParams = new ArrayList<>();
        joinedParams.addAll(params);
        joinedParams.addAll(params);
        return new IQueryPart(joinedSql, joinedParams);
    }

    @Override
    public String toString() {
        return "IQueryPart(sql=" + getSql() + ", params=" + getParams() + ")";
    }
}
