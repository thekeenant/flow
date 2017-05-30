package com.keenant.flow;

import com.keenant.flow.exp.FieldExp;
import com.keenant.flow.util.Transformer;

public interface MappedColumn<T, U> extends Exp, Transformer<T, U> {
    FieldExp getTable();

    String getName();
}
