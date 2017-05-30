package com.keenant.flow.exp;

import com.keenant.flow.Exp;
import com.keenant.flow.SQLDialect;

public class UpperExp extends AbstractUnaryExp {
    public UpperExp(Exp child) {
        super(child);
    }

    @Override
    protected String getSqlFormat(SQLDialect dialect) {
        return "UPPER(%s)";
    }
}
