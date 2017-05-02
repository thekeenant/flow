package com.keenant.flow.sql.impl.filter;

import com.keenant.flow.sql.Filter;
import com.keenant.flow.sql.SQLDialect;

public class NotFilter extends AbstractUnaryFilter {
    public NotFilter(Filter child) {
        super(child);
    }

    @Override
    protected String getSqlFormat(SQLDialect dialect) {
        return "NOT (%s)";
    }
}
