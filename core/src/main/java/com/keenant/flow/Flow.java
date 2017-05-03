package com.keenant.flow;

import com.keenant.flow.impl.DefaultConnector;
import com.keenant.flow.impl.IInsert;
import com.keenant.flow.impl.ISQLDatabase;
import com.keenant.flow.impl.ISelect;
import com.keenant.flow.impl.exp.AbsExp;
import com.keenant.flow.impl.exp.MaxExp;
import com.keenant.flow.impl.exp.WildcardExp;

public class Flow {
    public static DefaultConnector connector(String url) {
        return new DefaultConnector(url);
    }

    public static DatabaseContext database(SQLDialect dialect, Connector connector) {
        return new ISQLDatabase(dialect, connector);
    }

    public static Select selectFrom(Exp table) {
        return new ISelect(table);
    }

    public static Insert insertInto(Exp table) {
        return new IInsert(table);
    }

    // Todo: So many functions...

    public static WildcardExp wildcard() {
        return new WildcardExp();
    }

    public static AbsExp abs(Exp exp) {
        return new AbsExp(exp);
    }

    public static MaxExp max(Exp exp) {
        return new MaxExp(exp);
    }
}
