package com.keenant.flow.sql.impl.exp;

import com.keenant.flow.sql.Exp;
import com.keenant.flow.sql.SQLDialect;

public class UCaseExp extends AbstractUnaryExp {
    public UCaseExp(Exp child) {
        super(child);
    }

    @Override
    protected String getSqlFormat(SQLDialect dialect) {
        return "UPPER(%s)";
    }
}
