package com.keenant.flow;

/**
 * A "where ..." clause in SQL.
 */
public interface LogicalOperator {
    /**
     * Create a new filter which is this filter and'ed with another filter.
     *
     * @param other the filter to and with
     * @return the new filter
     */
    LogicalOperator and(LogicalOperator other);

    /**
     * Create a new filter which is this filter or'ed with another filter.
     *
     * @param other the filter to or with
     * @return the new filter
     */
    LogicalOperator or(LogicalOperator other);

    /**
     * Create a new filter that applies the 'not' or negation operator to this filter.
     *
     * @return the new inverted filter
     */
    LogicalOperator negate();
}
