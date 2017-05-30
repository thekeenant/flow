package com.keenant.flow.exp;

import com.keenant.flow.AbstractExp;
import com.keenant.flow.QueryPart;
import com.keenant.flow.SQLDialect;

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

    public FieldExp(FieldExp field, String column) {
        if (field.isQualified())
            throw new IllegalArgumentException("Base field cannot already be qualified");

        this.field = field.field;
        this.column = column;
    }

    public boolean isQualified() {
        return column != null;
    }

    @Override
    public QueryPart build(SQLDialect dialect) {
        String sql = dialect.wrapField(field);
        if (column != null) {
            sql += "." + dialect.wrapField(column);
        }
        return new QueryPart(sql);
    }
}
