package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;
import com.keenant.flow.impl.IInsert;
import com.keenant.flow.impl.ISQLDatabase;
import com.keenant.flow.impl.ISelect;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Flow {
    public static Connector connector(String url) {
        return () -> {
            try {
                return DriverManager.getConnection(url);
            } catch (SQLException e) {
                throw new DatabaseException(e);
            }
        };
    }

    public static SQLDatabase create(SQLDialect dialect, Connector connector) {
        return new ISQLDatabase(dialect, connector);
    }

    public static SQLDatabase open(SQLDialect dialect, Connector connector) {
        return create(dialect, connector).open();
    }

    public static Select selectFrom(Exp table) {
        return new ISelect(table);
    }

    public static Insert insertInto(Exp table) {
        return new IInsert(table);
    }
}
