package com.keenant.flow.sql.impl;

import com.keenant.flow.sql.Filter;
import com.keenant.flow.sql.impl.filter.AndFilter;
import com.keenant.flow.sql.impl.filter.NotFilter;
import com.keenant.flow.sql.impl.filter.OrFilter;

public abstract class AbstractFilter implements Filter {
    @Override
    public Filter and(Filter other) {
        return new AndFilter(this, other);
    }

    @Override
    public Filter or(Filter other) {
        return new OrFilter(this, other);
    }

    @Override
    public Filter not() {
        return new NotFilter(this);
    }
}
