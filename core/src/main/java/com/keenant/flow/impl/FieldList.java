package com.keenant.flow.impl;

import com.keenant.flow.Exp;
import com.keenant.flow.QueryPart;
import com.keenant.flow.SQLDialect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FieldList extends AbstractExp {
    private final Collection<Exp> fields;

    public FieldList(Collection<Exp> fields) {
        this.fields = fields;
    }

    @Override
    public QueryPart build(SQLDialect dialect) {
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<>();

        for (Exp field : fields) {
            QueryPart part = field.build(dialect);
            params.addAll(part.getParams());
            sql.append(part.getSql());
            sql.append(",");
        }
        sql.deleteCharAt(sql.length() - 1);

        return new IQueryPart(sql.toString(), params);
    }
}
