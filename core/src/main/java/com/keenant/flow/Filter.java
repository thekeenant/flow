package com.keenant.flow;

/**
 * An immutable, conditional SQL expression.
 */
public interface Filter {
    Filter and(Filter other);

    Filter or(Filter other);

    Filter not();

    QueryPart build(SQLDialect dialect);
}
