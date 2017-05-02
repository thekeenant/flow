package com.keenant.flow.sql;

public interface Filter {
    Filter and(Filter other);

    Filter or(Filter other);

    Filter not();

    QueryPart build(SQLDialect dialect);
}
