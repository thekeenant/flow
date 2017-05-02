package com.keenant.flow.sql.impl.exp;

import com.keenant.flow.sql.Exp;
import com.keenant.flow.sql.QueryPart;
import com.keenant.flow.sql.SQLDialect;
import com.keenant.flow.sql.impl.AbstractExp;
import com.keenant.flow.sql.impl.IQueryPart;

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

        return new IQueryPart(sql, childPart.getParams());
    }
}
