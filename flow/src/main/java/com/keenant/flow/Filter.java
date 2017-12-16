package com.keenant.flow;

/**
 * An immutable, conditional SQL expression.
 */
public interface Filter extends QueryPartBuilder {

  /**
   * @param other another filter to 'and' this one with
   * @return a new filter
   */
  Filter and(Filter other);

  /**
   * @param other another filter to 'or' this one with
   * @return a new filter
   */
  Filter or(Filter other);

  /**
   * @return a new filter, this one NOT'ed
   */
  Filter not();
}
