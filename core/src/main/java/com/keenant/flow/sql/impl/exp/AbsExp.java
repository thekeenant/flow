package com.keenant.flow.sql.impl.exp;

import com.keenant.flow.sql.Exp;
import com.keenant.flow.sql.SQLDialect;

import java.util.Collections;
import java.util.List;

public class AbsExp extends AbstractUnaryExp {
    public AbsExp(Exp child) {
        super(child);
    }

    @Override
    protected String getSqlFormat(SQLDialect dialect) {
        return "ABS(%s)";
    }
}
