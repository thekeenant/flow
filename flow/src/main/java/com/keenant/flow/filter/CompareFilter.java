package com.keenant.flow.filter;

import com.keenant.flow.*;

import java.util.ArrayList;
import java.util.List;

public class CompareFilter extends AbstractFilter {
    private final Exp leftSide;
    private final Exp rightSide;
    private final Comparator comparator;

    public CompareFilter(Exp left, Exp right, Comparator comparator) {
        this.leftSide = left;
        this.rightSide = right;
        this.comparator = comparator;
    }

    @Override
    public QueryPart build(SQLDialect dialect) {
        QueryPart lhs = leftSide.build(dialect);
        QueryPart rhs = rightSide.build(dialect);

        String sql = lhs.getSql();
        sql += " " + comparator.getSql() + " ";
        sql += rhs.getSql();

        List<Object> params = new ArrayList<>();
        params.addAll(lhs.getParams());
        params.addAll(rhs.getParams());

        return new QueryPart(sql, params);
    }
}
