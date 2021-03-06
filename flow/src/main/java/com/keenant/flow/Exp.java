package com.keenant.flow;

import com.keenant.flow.exp.AliasExp;
import com.keenant.flow.exp.AsExp;
import com.keenant.flow.exp.FieldExp;
import com.keenant.flow.exp.OnExp;
import com.keenant.flow.exp.ParamExp;
import com.keenant.flow.exp.functions.*;
import com.keenant.flow.jdbc.Order;
import java.lang.reflect.Parameter;
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
   * @return the IN filter
   */
  Filter in(Exp other);

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

  default MathExp add(Exp value) {
    return new MathExp(this, value, Operator.ADD);
  }

  default MathExp add(Object value) {
    return add(new ParamExp(value));
  }

  default MathExp plus(Exp value) {
    return add(value);
  }

  default MathExp plus(Object value) {
    return add(value);
  }

  default MathExp sub(Exp value) {
    return new MathExp(this, value, Operator.SUBTRACT);
  }

  default MathExp sub(Object value) {
    return sub(new ParamExp(value));
  }

  default MathExp minus(Exp value) {
    return sub(value);
  }

  default MathExp minus(Object value) {
    return sub(new ParamExp(value));
  }

  default MathExp div(Exp value) {
    return new MathExp(this, value, Operator.DIVIDE);
  }

  default MathExp div(Object value) {
    return div(new ParamExp(value));
  }

  default MathExp mult(Exp value) {
    return new MathExp(this, value, Operator.MULTIPLY);
  }

  default MathExp mult(Object value) {
    return mult(new ParamExp(value));
  }

  default MathExp mod(Exp value) {
    return new MathExp(this, value, Operator.MODULO);
  }

  default MathExp mod(Object value) {
     return mod(new ParamExp(value));
  }

  // Keywords

  default OnExp on(Filter filter) {
    return new OnExp(this, filter);
  }

  default AsExp as(AliasExp alias) {
    return new AsExp(this, alias);
  }

  default AsExp as(String alias) {
    return as(new AliasExp(alias));
  }

  default FieldExp qualify(Exp qualifier) {
    return new FieldExp(this, qualifier);
  }

  default FieldExp specify(Exp child) {
    return specify(child, true);
  }

  default FieldExp specify(Exp child, boolean overrideQualifier) {
    Exp result = child;

    if (child instanceof FieldExp) {
      FieldExp childField = (FieldExp) child;
      if (overrideQualifier) {
        result = childField.getField();
      }
    }

    return new FieldExp(result, this);
  }
}
