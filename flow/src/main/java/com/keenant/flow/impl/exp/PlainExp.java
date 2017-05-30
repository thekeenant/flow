package com.keenant.flow.impl.exp;

import com.keenant.flow.QueryPart;
import com.keenant.flow.SQLDialect;
import com.keenant.flow.impl.QueryPartImpl;

import java.util.Arrays;
import java.util.List;

/**
 * A raw SQL expression.
 */
public class PlainExp extends AbstractExp {
    private final QueryPart part;

    public PlainExp(String sql, List<Object> params) {
        this.part = new QueryPartImpl(sql, params);
    }

    public PlainExp(String sql, Object... params) {
        this(sql, Arrays.asList(params));
    }

    @Override
    public QueryPart build(SQLDialect dialect) {
        return part;
    }
}
