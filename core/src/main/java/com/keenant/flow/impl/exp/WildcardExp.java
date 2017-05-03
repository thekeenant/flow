package com.keenant.flow.impl.exp;

import com.keenant.flow.QueryPart;
import com.keenant.flow.SQLDialect;
import com.keenant.flow.impl.AbstractExp;
import com.keenant.flow.impl.IQueryPart;

public class WildcardExp extends AbstractExp {
    private static final QueryPart QUERY_PART = new IQueryPart("*");

    @Override
    public QueryPart build(SQLDialect dialect) {
        return QUERY_PART;
    }
}
