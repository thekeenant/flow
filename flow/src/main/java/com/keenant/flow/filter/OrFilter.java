package com.keenant.flow.filter;

import com.keenant.flow.AbstractBinaryFilter;
import com.keenant.flow.Filter;
import com.keenant.flow.SQLDialect;

public class OrFilter extends AbstractBinaryFilter {
    public OrFilter(Filter filter1, Filter filter2) {
        super(filter1, filter2);
    }

    @Override
    protected String getSqlFormat(SQLDialect dialect) {
        return "(%s) OR (%s)";
    }
}
