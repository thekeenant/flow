package com.keenant.flow.impl.exp;

import com.keenant.flow.Exp;
import com.keenant.flow.SQLDialect;

public class LCaseExp extends AbstractUnaryExp {
    public LCaseExp(Exp child) {
        super(child);
    }

    @Override
    protected String getSqlFormat(SQLDialect dialect) {
        return "LOWER(%s)";
    }
}
