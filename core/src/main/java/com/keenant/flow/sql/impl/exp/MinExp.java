package com.keenant.flow.sql.impl.exp;

import com.keenant.flow.sql.Exp;
import com.keenant.flow.sql.SQLDialect;

public class MinExp extends AbstractUnaryExp {
    public MinExp(Exp child) {
        super(child);
    }

    @Override
    protected String getSqlFormat(SQLDialect dialect) {
        return "MIN(%s)";
    }
}
