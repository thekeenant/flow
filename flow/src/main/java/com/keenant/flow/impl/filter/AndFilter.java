package com.keenant.flow.impl.filter;

import com.keenant.flow.Filter;
import com.keenant.flow.SQLDialect;

public class AndFilter extends AbstractBinaryFilter {
    public AndFilter(Filter filter1, Filter filter2) {
        super(filter1, filter2);
    }

    @Override
    protected String getSqlFormat(SQLDialect dialect) {
        return "(%s) AND (%s)";
    }
}
