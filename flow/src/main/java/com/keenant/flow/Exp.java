package com.keenant.flow;

import com.keenant.flow.exp.AsExp;
import com.keenant.flow.exp.FieldExp;
import com.keenant.flow.exp.OnExp;
import com.keenant.flow.exp.ParamExp;
import com.keenant.flow.exp.functions.AbsExp;
import com.keenant.flow.exp.functions.AvgExp;
import com.keenant.flow.exp.functions.CountExp;
import com.keenant.flow.exp.functions.LengthExp;
import com.keenant.flow.exp.functions.ListExp;
import com.keenant.flow.exp.functions.LowerExp;
import com.keenant.flow.exp.functions.MaxExp;
import com.keenant.flow.exp.functions.OrderExp;
import com.keenant.flow.exp.functions.SumExp;
import com.keenant.flow.exp.functions.UpperExp;
import com.keenant.flow.jdbc.Order;
import java.util.Collections;

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

  // Functions

  default AbsExp abs() {
    return new AbsExp(this);
  }

  default AvgExp avg() {
    return new AvgExp(this);
  }

  default CountExp count() {
    return new CountExp(this);
  }

  default LengthExp length() {
    return new LengthExp(this);
  }

  default ListExp list() {
    return new ListExp(Collections.singleton(this));
  }

  default LowerExp lower() {
    return new LowerExp(this);
  }

  default MaxExp max() {
    return new MaxExp(this);
  }

  default OrderExp order(Order order) {
    return new OrderExp(this, order);
  }

  default OrderExp orderAsc() {
    return order(Order.ASC);
  }

  default OrderExp orderDesc() {
    return order(Order.DESC);
  }

  default SumExp sum() {
    return new SumExp(this);
  }

  default UpperExp upper() {
    return new UpperExp(this);
  }

  // Keywords

  default OnExp on(Filter filter) {
    return new OnExp(this, filter);
  }

  default AsExp as(Exp as) {
    return new AsExp(this, as);
  }

  default AsExp as(Object as) {
    return as(new ParamExp(as));
  }

  default FieldExp qualify(Exp qualifier) {
    return new FieldExp(this, qualifier);
  }
}
