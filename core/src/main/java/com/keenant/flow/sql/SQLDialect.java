package com.keenant.flow.sql;

public class SQLDialect {
    public static final SQLDialect SQLITE = new SQLDialect("SQLite", false);
    public static final SQLDialect MYSQL = new SQLDialect("SQLite", false);

    private final String name;
    private final boolean scrolling;

    public SQLDialect(String name, boolean scrolling) {
        this.name = name;
        this.scrolling = scrolling;
    }

    public boolean supportsScrolling() {
        return scrolling;
    }
}
