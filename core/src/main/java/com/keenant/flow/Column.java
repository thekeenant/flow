package com.keenant.flow;

import com.keenant.flow.impl.exp.FieldExp;

public interface Column<T, U> extends Exp, Transformer<T, U> {
    FieldExp getTable();

    String getName();
}
