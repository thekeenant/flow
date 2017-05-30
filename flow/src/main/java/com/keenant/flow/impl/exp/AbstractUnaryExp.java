package com.keenant.flow.impl.exp;

import com.keenant.flow.Exp;
import com.keenant.flow.QueryPart;
import com.keenant.flow.SQLDialect;
import com.keenant.flow.impl.QueryPartImpl;

/**
 * An expression that takes one parameter.
 */
public abstract class AbstractUnaryExp extends AbstractExp {
    private final Exp child;

    public AbstractUnaryExp(Exp child) {
        this.child = child;
    }

    protected abstract String getSqlFormat(SQLDialect dialect);

    @Override
    public QueryPart build(SQLDialect dialect) {
        QueryPart childPart = child.build(dialect);

        String sql = String.format(getSqlFormat(dialect), childPart.getSql());

        return new QueryPartImpl(sql, childPart.getParams());
    }
}
