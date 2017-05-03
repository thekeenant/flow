package com.keenant.flow.impl.exp;

import com.keenant.flow.QueryPart;
import com.keenant.flow.SQLDialect;
import com.keenant.flow.impl.AbstractExp;
import com.keenant.flow.impl.IQueryPart;

/**
 * An expression that is a single parameter/object.
 */
public class ParamExp extends AbstractExp {
    private final Object param;

    public ParamExp(Object param) {
        this.param = param;
    }

    @Override
    public QueryPart build(SQLDialect dialect) {
        return new IQueryPart("?", param);
    }
}
