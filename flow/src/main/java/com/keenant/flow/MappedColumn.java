package com.keenant.flow;

import com.keenant.flow.exp.Field;
import com.keenant.flow.util.Transformer;

public interface MappedColumn<T, U> extends Exp, Transformer<T, U> {
    Field getTable();

    String getName();
}
