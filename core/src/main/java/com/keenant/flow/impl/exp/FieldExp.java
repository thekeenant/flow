package com.keenant.flow.impl.exp;

import com.keenant.flow.QueryPart;
import com.keenant.flow.SQLDialect;
import com.keenant.flow.impl.AbstractExp;
import com.keenant.flow.impl.IQueryPart;

public class FieldExp extends AbstractExp {
    private final String field;
    private String column;

    public FieldExp(String field) {
        this.field = field;
    }

    public FieldExp(String field, String column) {
        this(field);
        this.column = column;
    }

    @Override
    public QueryPart build(SQLDialect dialect) {
        String sql = dialect.wrapField(field);
        if (column != null) {
            sql += "." + dialect.wrapField(field);
        }
        return new IQueryPart(sql);
    }
}
