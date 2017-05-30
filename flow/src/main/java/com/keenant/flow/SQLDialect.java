package com.keenant.flow;

import java.util.function.Function;

public class SQLDialect {
    /**
     * SQLite SQL dialect.
     */
    public static final SQLDialect SQLITE = new SQLDialect(
            "SQLite",
            str -> '"' + str + '"',
            false
    );

    /**
     * MySQL SQL dialect.
     */
    public static final SQLDialect MYSQL = new SQLDialect(
            "MySQL",
            str -> '`' + str + '`',
            true
    );

    private final String name;
    private final Function<String, String> fieldWrapper;
    private final boolean scrolling;

    public SQLDialect(String name, Function<String, String> fieldWrapper, boolean scrolling) {
        this.name = name;
        this.fieldWrapper = fieldWrapper;
        this.scrolling = scrolling;
    }

    public String wrapField(String field) {
        return fieldWrapper.apply(field);
    }

    public boolean supportsScrolling() {
        return scrolling;
    }
}
