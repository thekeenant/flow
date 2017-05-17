package com.keenant.flow.impl;

import com.keenant.flow.*;
import com.keenant.flow.impl.exp.LCaseExp;
import com.keenant.flow.impl.exp.ParamExp;
import com.keenant.flow.impl.filter.CompareFilter;
import com.keenant.flow.impl.filter.ExpFilter;
import com.keenant.flow.impl.filter.NotFilter;

public abstract class AbstractExp implements Exp {
    @Override
    public Filter filter() {
        return new ExpFilter(this);
    }

    @Override
    public Filter not() {
        return new NotFilter(filter());
    }

    @Override
    public Filter like(Exp other) {
        return new CompareFilter(this, other, Comparator.LIKE);
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
    public Filter equalIgnoreCase(Exp other) {
        return new LCaseExp(this).eq(new LCaseExp(this));
    }

    @Override
    public Filter equalIgnoreCase(Object other) {
        return equalIgnoreCase(new ParamExp(other));
    }

    @Override
    public Filter notEqualIgnoreCase(Exp other) {
        return new LCaseExp(this).neq(new LCaseExp(this));
    }

    @Override
    public Filter notEqualIgnoreCase(Object other) {
        return notEqualIgnoreCase(new ParamExp(other));
    }

    @Override
    public Filter neq(Exp other) {
        return new CompareFilter(this, other, Comparator.NOT_EQUAL);
    }

    @Override
    public Filter neq(Object other) {
        return neq(new ParamExp(other));
    }

    @Override
    public Filter notEqual(Exp other) {
        return neq(other);
    }

    @Override
    public Filter notEqual(Object other) {
        return neq(other);
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
