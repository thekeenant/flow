package com.keenant.flow.impl.exp;

import com.keenant.flow.Exp;
import com.keenant.flow.SQLDialect;
import com.keenant.flow.impl.Order;

public class OrderExp extends AbstractUnaryExp {
    private final Order order;

    public OrderExp(Exp child, Order order) {
        super(child);
        this.order = order;
    }

    @Override
    protected String getSqlFormat(SQLDialect dialect) {
        return "%s " + order.getSql();
    }
}
