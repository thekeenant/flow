package com.keenant.flow.impl.exp;

import com.keenant.flow.QueryPart;
import com.keenant.flow.SQLDialect;
import com.keenant.flow.impl.AbstractExp;
import com.keenant.flow.impl.IQueryPart;

import java.util.List;

/**
 * A raw SQL expression.
 */
public class InlineExp extends AbstractExp {
    private final QueryPart part;

    public InlineExp(String sql) {
        this.part = new IQueryPart(sql);
    }

    @Override
    public QueryPart build(SQLDialect dialect) {
        return part;
    }
}