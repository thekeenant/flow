package com.keenant.flow.impl.exp;

import com.keenant.flow.Exp;
import com.keenant.flow.SQLDialect;

public class MaxExp extends AbstractUnaryExp {
    public MaxExp(Exp child) {
        super(child);
    }

    @Override
    protected String getSqlFormat(SQLDialect dialect) {
        return "MAX(%s)";
    }
}
