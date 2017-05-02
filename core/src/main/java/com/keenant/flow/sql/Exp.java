package com.keenant.flow.sql;

/**
 * A DSL (SQL) expression.
 */
public interface Exp {
    QueryPart build(SQLDialect dialect);

    Exp distinct();

    Exp asc();

    Exp desc();

    Exp abs();

    Exp sum();

    Exp max();

    Exp min();

    Exp ucase();

    Exp lcase();

    Exp not();
}
