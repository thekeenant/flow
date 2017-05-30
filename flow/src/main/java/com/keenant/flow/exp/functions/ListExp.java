package com.keenant.flow.exp.functions;

import com.keenant.flow.AbstractExp;
import com.keenant.flow.Exp;
import com.keenant.flow.QueryPart;
import com.keenant.flow.SQLDialect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListExp extends AbstractExp {
    private final Collection<Exp> expressions;

    public ListExp(Collection<Exp> expressions) {
        this.expressions = expressions;
    }

    @Override
    public QueryPart build(SQLDialect dialect) {
        StringBuilder sb = new StringBuilder();
        List<Object> params = new ArrayList<>();

        for (Exp exp : expressions) {
            QueryPart part = exp.build(dialect);
            sb.append(part.getSql());
            sb.append(",");
            params.addAll(part.getParams());
        }
        sb.deleteCharAt(sb.length() - 1);


        return new QueryPart(sb.toString(), params);
    }
}
