package com.keenant.flow.impl.exp;

import com.keenant.flow.QueryPart;
import com.keenant.flow.SQLDialect;
import com.keenant.flow.impl.AbstractExp;
import com.keenant.flow.impl.IQueryPart;

public class FieldExp extends AbstractExp {
    private final String field;

    public FieldExp(String field) {
        this.field = field;
    }

    @Override
    public QueryPart build(SQLDialect dialect) {
        return new IQueryPart(dialect.wrapField(field));
    }
}
