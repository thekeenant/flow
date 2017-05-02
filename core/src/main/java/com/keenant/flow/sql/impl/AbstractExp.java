package com.keenant.flow.sql.impl;

import com.keenant.flow.sql.Exp;
import com.keenant.flow.sql.impl.exp.*;

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
    public Exp not() {
        return null;
    }
}
