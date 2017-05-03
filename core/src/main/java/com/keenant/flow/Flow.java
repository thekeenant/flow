package com.keenant.flow;

import com.keenant.flow.impl.DefaultConnector;
import com.keenant.flow.impl.IInsert;
import com.keenant.flow.impl.ISQLDatabase;
import com.keenant.flow.impl.ISelect;
import com.keenant.flow.impl.exp.AbsExp;
import com.keenant.flow.impl.exp.InlineExp;
import com.keenant.flow.impl.exp.MaxExp;

public class Flow {
    private static final Exp WILDCARD = new InlineExp("*");

    public static DefaultConnector connector(String url) {
        return new DefaultConnector(url);
    }

    public static DatabaseContext database(SQLDialect dialect, Connector connector) {
        return new ISQLDatabase(dialect, connector);
    }

    public static DatabaseContext database(SQLDialect dialect, String url) {
        return database(dialect, connector(url));
    }

    public static Select selectFrom(Exp table) {
        return new ISelect(table);
    }

    public static Insert insertInto(Exp table) {
        return new IInsert(table);
    }

    // Todo: So many functions...

    public static Exp wildcard() {
        return WILDCARD;
    }

    public static Exp inline(String sql) {
        return new InlineExp(sql);
    }

    public static AbsExp abs(Exp exp) {
        return new AbsExp(exp);
    }

    public static MaxExp max(Exp exp) {
        return new MaxExp(exp);
    }
}
