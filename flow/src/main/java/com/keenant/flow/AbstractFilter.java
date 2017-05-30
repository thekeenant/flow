package com.keenant.flow;

import com.keenant.flow.Filter;
import com.keenant.flow.filter.AndFilter;
import com.keenant.flow.filter.NotFilter;
import com.keenant.flow.filter.OrFilter;

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
