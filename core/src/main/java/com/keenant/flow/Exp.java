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
     * @return the LIKE filter
     */
    Filter like(Exp other);

    /**
     * @param other the right-hand-side
     * @return equality filter
     */
    Filter eq(Exp other);

    /**
     * @see #eq(Exp)
     */
    Filter eq(Object other);

    /**
     * @see #eq(Exp)
     */
    Filter equal(Exp other);

    /**
     * @see #eq(Exp)
     */
    Filter equal(Object other);

    /**
     * @param other the right-hand-side
     * @return equality filter for strings, with case ignored
     */
    Filter equalIgnoreCase(Exp other);

    /**
     * @see #equalIgnoreCase(Exp)
     */
    Filter equalIgnoreCase(Object other);

    /**
     * @param other the right-hand-side
     * @return inverse equality filter for strings, with case ignored
     */
    Filter notEqualIgnoreCase(Exp other);

    /**
     * @see #notEqualIgnoreCase(Exp)
     */
    Filter notEqualIgnoreCase(Object other);

    /**
     * @param other the right-hand-side
     * @return inverse equality filter
     */
    Filter neq(Exp other);

    /**
     * @see #neq(Exp)
     */
    Filter neq(Object other);

    /**
     * @see #neq(Exp)
     */
    Filter notEqual(Exp other);

    /**
     * @see #neq(Exp)
     */
    Filter notEqual(Object other);

    /**
     * @param other the right-hand-side
     * @return less than filter
     */
    Filter lt(Exp other);

    /**
     * @see #lt(Exp)
     */
    Filter lt(Object other);

    /**
     * @see #lt(Exp)
     */
    Filter lessThan(Exp other);

    /**
     * @see #lt(Exp)
     */
    Filter lessThan(Object other);

    /**
     * @param other the right-hand-side
     * @return greater than filter
     */
    Filter gt(Exp other);

    /**
     * @see #gt(Exp)
     */
    Filter gt(Object other);

    /**
     * @see #gt(Exp)
     */
    Filter greaterThan(Exp other);

    /**
     * @see #gt(Exp)
     */
    Filter greaterThan(Object other);

    /**
     * @param other the right-hand-side
     * @return less than or equal to filter
     */
    Filter lte(Exp other);

    /**
     * @see #lte(Exp)
     */
    Filter lte(Object other);

    /**
     * @see #lte(Exp)
     */
    Filter lessThanEqual(Exp other);

    /**
     * @see #lte(Exp)
     */
    Filter lessThanEqual(Object other);

    /**
     * @param other the right-hand-side
     * @return greater than or equal to filter
     */
    Filter gte(Exp other);

    /**
     * @see #gte(Exp)
     */
    Filter gte(Object other);

    /**
     * @see #gte(Exp)
     */
    Filter greaterThanEqual(Exp other);

    /**
     * @see #gte(Exp)
     */
    Filter greaterThanEqual(Object other);
}
