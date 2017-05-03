package com.keenant.flow.impl;

import com.keenant.flow.Comparator;
import com.keenant.flow.Exp;
import com.keenant.flow.Filter;
import com.keenant.flow.impl.exp.*;
import com.keenant.flow.impl.filter.CompareFilter;
import com.keenant.flow.impl.filter.ExpFilter;
import com.keenant.flow.impl.filter.NotFilter;

public abstract class AbstractExp implements Exp {
    @Override
    public Exp distinct() {
        return null;
    }

    @Override
    public Exp asc() {
        return new OrderExp(this, Order.ASC);
    }

    @Override
    public Exp desc() {
        return new OrderExp(this, Order.DESC);
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
    public Exp length() {
        return new LengthExp(this);
    }

    // Filters

    @Override
    public Filter filter() {
        return new ExpFilter(this);
    }

    @Override
    public Filter not() {
        return new NotFilter(filter());
    }

    @Override
    public Filter eq(Exp other) {
        return new CompareFilter(this, other, Comparator.EQUALS);
    }

    @Override
    public Filter eq(Object other) {
        return eq(new ParamExp(other));
    }

    @Override
    public Filter equal(Exp other) {
        return eq(other);
    }

    @Override
    public Filter equal(Object other) {
        return eq(other);
    }

    @Override
    public Filter lt(Exp other) {
        return new CompareFilter(this, other, Comparator.LESS);
    }

    @Override
    public Filter lt(Object other) {
        return lt(new ParamExp(other));
    }

    @Override
    public Filter lessThan(Exp other) {
        return lt(other);
    }

    @Override
    public Filter lessThan(Object other) {
        return lt(other);
    }

    @Override
    public Filter gt(Exp other) {
        return new CompareFilter(this, other, Comparator.GREATER);
    }

    @Override
    public Filter gt(Object other) {
        return gt(new ParamExp(other));
    }

    @Override
    public Filter greaterThan(Exp other) {
        return gt(other);
    }

    @Override
    public Filter greaterThan(Object other) {
        return gt(other);
    }

    @Override
    public Filter lte(Exp other) {
        return new CompareFilter(this, other, Comparator.LESS_OR_EQUAL);
    }

    @Override
    public Filter lte(Object other) {
        return lte(new ParamExp(other));
    }

    @Override
    public Filter lessThanEqual(Exp other) {
        return lte(other);
    }

    @Override
    public Filter lessThanEqual(Object other) {
        return lte(other);
    }

    @Override
    public Filter gte(Exp other) {
        return new CompareFilter(this, other, Comparator.GREATER_OR_EQUAL);
    }

    @Override
    public Filter gte(Object other) {
        return gte(new ParamExp(other));
    }

    @Override
    public Filter greaterThanEqual(Exp other) {
        return gte(other);
    }

    @Override
    public Filter greaterThanEqual(Object other) {
        return gte(other);
    }
}
