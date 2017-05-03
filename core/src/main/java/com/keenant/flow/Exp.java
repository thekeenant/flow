package com.keenant.flow;

/**
 * A DSL (SQL) expression.
 */
public interface Exp {
    /**
     * Construct the SQL query part
     * @param dialect the SQL dialect to use
     * @return the SQL query component
     */
    QueryPart build(SQLDialect dialect);

    // Filters

    Filter filter();

    Filter not();

    Filter eq(Exp other);

    Filter eq(Object other);

    Filter equal(Exp other);

    Filter equal(Object other);

    Filter equalIgnoreCase(Exp other);

    Filter equalIgnoreCase(Object other);

    Filter notEqualIgnoreCase(Exp other);

    Filter notEqualIgnoreCase(Object other);

    Filter neq(Exp other);

    Filter neq(Object other);

    Filter notEqual(Exp other);

    Filter notEqual(Object other);

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
