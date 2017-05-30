package com.keenant.flow.exp.functions;

import com.keenant.flow.AbstractUnaryExp;
import com.keenant.flow.Exp;
import com.keenant.flow.SQLDialect;

public class LowerExp extends AbstractUnaryExp {
    public LowerExp(Exp child) {
        super(child);
    }

    @Override
    protected String getSqlFormat(SQLDialect dialect) {
        return "LOWER(%s)";
    }
}
