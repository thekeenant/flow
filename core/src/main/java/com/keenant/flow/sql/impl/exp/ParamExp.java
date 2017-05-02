package com.keenant.flow.sql.impl.exp;

import com.keenant.flow.sql.QueryPart;
import com.keenant.flow.sql.SQLDialect;
import com.keenant.flow.sql.impl.AbstractExp;
import com.keenant.flow.sql.impl.IQueryPart;

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
