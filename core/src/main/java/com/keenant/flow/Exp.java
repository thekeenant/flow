package com.keenant.flow;

/**
 * A DSL (SQL) expression.
 */
public interface Exp extends QueryPartBuilder {
    /**
     * @return this expression as a filter
     */
    Filter filter();

    /**
     * @return invert this filter (NOT'ed).
     */
    Filter not();

    /**
     * @param other the right-hand-side
     * @return equality filter
     */
    Filter eq(Exp other);

    /**
     * See {@link #eq(Exp)}.
     */
    Filter eq(Object other);

    /**
     * See {@link #eq(Exp)}.
     */
    Filter equal(Exp other);

    /**
     * See {@link #eq(Exp)}.
     */
    Filter equal(Object other);

    /**
     * @param other the right-hand-side
     * @return equality filter for strings, with case ignored
     */
    Filter equalIgnoreCase(Exp other);

    /**
     * See {@link #equalIgnoreCase(Exp)}.
     */
    Filter equalIgnoreCase(Object other);

    /**
     * @param other the right-hand-side
     * @return inverse equality filter for strings, with case ignored
     */
    Filter notEqualIgnoreCase(Exp other);

    /**
     * See {@link #notEqualIgnoreCase(Exp)}.
     */
    Filter notEqualIgnoreCase(Object other);

    /**
     * @param other the right-hand-side
     * @return inverse equality filter
     */
    Filter neq(Exp other);

    /**
     * See {@link #neq(Exp)}.
     */
    Filter neq(Object other);

    /**
     * See {@link #neq(Exp)}.
     */
    Filter notEqual(Exp other);

    /**
     * See {@link #neq(Exp)}.
     */
    Filter notEqual(Object other);

    /**
     * @param other the right-hand-side
     * @return less than filter
     */
    Filter lt(Exp other);

    /**
     * See {@link #lt(Exp)}.
     */
    Filter lt(Object other);

    /**
     * See {@link #lt(Exp)}.
     */
    Filter lessThan(Exp other);

    /**
     * See {@link #lt(Exp)}.
     */
    Filter lessThan(Object other);

    /**
     * @param other the right-hand-side
     * @return greater than filter
     */
    Filter gt(Exp other);

    /**
     * See {@link #gt(Exp)}.
     */
    Filter gt(Object other);

    /**
     * See {@link #gt(Exp)}.
     */
    Filter greaterThan(Exp other);

    /**
     * See {@link #gt(Exp)}.
     */
    Filter greaterThan(Object other);

    /**
     * @param other the right-hand-side
     * @return less than or equal to filter
     */
    Filter lte(Exp other);

    /**
     * See {@link #gte(Exp)}.
     */
    Filter lte(Object other);

    /**
     * See {@link #gte(Exp)}.
     */
    Filter lessThanEqual(Exp other);

    /**
     * See {@link #gte(Exp)}.
     */
    Filter lessThanEqual(Object other);

    /**
     * @param other the right-hand-side
     * @return greater than or equal to filter
     */
    Filter gte(Exp other);

    /**
     * See {@link #gte(Exp)}.
     */
    Filter gte(Object other);

    /**
     * See {@link #gte(Exp)}.
     */
    Filter greaterThanEqual(Exp other);

    /**
     * See {@link #gte(Exp)}.
     */
    Filter greaterThanEqual(Object other);
}
