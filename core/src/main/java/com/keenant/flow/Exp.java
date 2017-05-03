package com.keenant.flow;

/**
 * A DSL (SQL) expression.
 */
public interface Exp {
    QueryPart build(SQLDialect dialect);

    // Functions

    Exp distinct();

    Exp asc();

    Exp desc();

    Exp abs();

    Exp sum();

    Exp max();

    Exp min();

    Exp ucase();

    Exp lcase();

    Exp length();

    // Filters

    Filter filter();

    Filter not();

    Filter eq(Exp other);

    Filter eq(Object other);

    Filter equal(Exp other);

    Filter equal(Object other);

    Filter lt(Exp other);

    Filter lt(Object other);

    Filter lessThan(Exp other);

    Filter lessThan(Object other);

    Filter gt(Exp other);

    Filter gt(Object other);

    Filter greaterThan(Exp other);

    Filter greaterThan(Object other);

    Filter lte(Exp other);

    Filter lte(Object other);

    Filter lessThanEqual(Exp other);

    Filter lessThanEqual(Object other);

    Filter gte(Exp other);

    Filter gte(Object other);

    Filter greaterThanEqual(Exp other);

    Filter greaterThanEqual(Object other);
}
