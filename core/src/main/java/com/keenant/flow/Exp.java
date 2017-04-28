package com.keenant.flow;

/**
 * A DSL (SQL) expression.
 */
public interface Exp extends LogicalOperator {
    LogicalOperator isNull();
}
