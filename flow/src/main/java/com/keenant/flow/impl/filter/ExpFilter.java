package com.keenant.flow.impl.filter;

import com.keenant.flow.Exp;
import com.keenant.flow.QueryPart;
import com.keenant.flow.SQLDialect;
import com.keenant.flow.impl.AbstractFilter;

/**
 * A filter that is simply an expression.
 */
public class ExpFilter extends AbstractFilter {
    private final Exp exp;

    public ExpFilter(Exp exp) {
        this.exp = exp;
    }

    @Override
    public QueryPart build(SQLDialect dialect) {
        return exp.build(dialect);
    }
}
