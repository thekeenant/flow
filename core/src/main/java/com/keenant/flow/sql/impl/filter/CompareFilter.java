package com.keenant.flow.sql.impl.filter;

import com.keenant.flow.sql.Exp;
import com.keenant.flow.sql.QueryPart;
import com.keenant.flow.sql.SQLDialect;
import com.keenant.flow.sql.impl.AbstractFilter;
import com.keenant.flow.sql.impl.IQueryPart;

import java.util.ArrayList;
import java.util.List;

public class CompareFilter extends AbstractFilter {
    private final Exp leftSide;
    private final Exp rightSide;
    private final Comparator comparator;

    public enum Comparator {
        EQUALS("="),
        LESS("<"),
        GREATER("<");

        private final String symbol;

        Comparator(String symbol) {
            this.symbol = symbol;
        }
    }

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
        sql += " " + comparator.symbol + " ";
        sql += rhs.getSql();

        List<Object> params = new ArrayList<>();
        params.addAll(lhs.getParams());
        params.addAll(rhs.getParams());

        return new IQueryPart(sql, params);
    }
}
