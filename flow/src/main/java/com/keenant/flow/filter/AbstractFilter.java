package com.keenant.flow.filter;

import com.keenant.flow.Filter;

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
