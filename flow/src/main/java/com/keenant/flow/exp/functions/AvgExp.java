package com.keenant.flow.exp.functions;

import com.keenant.flow.AbstractUnaryExp;
import com.keenant.flow.Exp;
import com.keenant.flow.SQLDialect;

public class AvgExp extends AbstractUnaryExp {
    public AvgExp(Exp child) {
        super(child);
    }

    @Override
    protected String getSqlFormat(SQLDialect dialect) {
        return "AVG(%s)";
    }
}
