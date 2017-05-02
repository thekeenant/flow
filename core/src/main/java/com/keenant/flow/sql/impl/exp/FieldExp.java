package com.keenant.flow.sql.impl.exp;

import com.keenant.flow.sql.QueryPart;
import com.keenant.flow.sql.SQLDialect;
import com.keenant.flow.sql.impl.AbstractExp;
import com.keenant.flow.sql.impl.IQueryPart;

public class FieldExp extends AbstractExp {
    private final String field;

    public FieldExp(String field) {
        this.field = field;
    }

    @Override
    public QueryPart build(SQLDialect dialect) {
        return new IQueryPart(field);
    }
}
