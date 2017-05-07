package com.keenant.flow;

import com.keenant.flow.impl.exp.Field;

public interface MappedColumn<T, U> extends Exp, Transformer<T, U> {
    Field getTable();

    String getName();
}
