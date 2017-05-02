package com.keenant.flow.sql.impl.filter;

import com.keenant.flow.sql.Filter;
import com.keenant.flow.sql.SQLDialect;

public class OrFilter extends AbstractBinaryFilter {
    public OrFilter(Filter filter1, Filter filter2) {
        super(filter1, filter2);
    }

    @Override
    protected String getSqlFormat(SQLDialect dialect) {
        return "(%s) OR (%s)";
    }
}
