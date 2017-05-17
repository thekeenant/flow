package com.keenant.flow.impl.exp;

import com.keenant.flow.QueryPart;
import com.keenant.flow.SQLDialect;
import com.keenant.flow.impl.AbstractExp;
import com.keenant.flow.impl.IQueryPart;

public class Field extends AbstractExp {
    private final String field;
    private String column;

    public Field(String field) {
        this.field = field;
    }

    public Field(String field, String column) {
        this(field);
        this.column = column;
    }

    public Field(Field field, String column) {
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
        return new IQueryPart(sql);
    }
}
