package com.keenant.flow.sql.impl;

import com.keenant.flow.sql.Exp;
import com.keenant.flow.sql.Filter;
import com.keenant.flow.sql.QueryPart;
import com.keenant.flow.sql.SQLDialect;
import com.keenant.flow.sql.impl.exp.*;
import com.keenant.flow.sql.impl.filter.CompareFilter;
import com.keenant.flow.sql.impl.filter.CompareFilter.Comparator;
import com.keenant.flow.sql.impl.filter.NotFilter;

public abstract class AbstractExp implements Exp {
    @Override
    public Exp distinct() {
        return null;
    }

    @Override
    public Exp asc() {
        return null;
    }

    @Override
    public Exp desc() {
        return null;
    }

    @Override
    public Exp abs() {
        return new AbsExp(this);
    }

    @Override
    public Exp sum() {
        return new SumExp(this);
    }

    @Override
    public Exp max() {
        return new MaxExp(this);
    }

    @Override
    public Exp min() {
        return new MinExp(this);
    }

    @Override
    public Exp ucase() {
        return new UCaseExp(this);
    }

    @Override
    public Exp lcase() {
        return new LCaseExp(this);
    }

    @Override
    public Filter eq(Exp other) {
        return new CompareFilter(this, other, Comparator.EQUALS);
    }

    @Override
    public Filter not() {
        return new NotFilter(this);
    }
}
