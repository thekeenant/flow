package com.keenant.flow.impl;

import com.keenant.flow.*;
import com.keenant.flow.impl.exp.Field;

public class IColumn<T, U> extends Field implements Column<T, U> {
    private final Field table;
    private final String name;
    private final Transformer<T, U> transformer;

    public IColumn(Field table, String name, Transformer<T, U> transformer) {
        super(table, name);
        this.table = table;
        this.name = name;
        this.transformer = transformer;
    }

    @Override
    public Field getTable() {
        return table;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public U to(T sourceObject) {
        return transformer.to(sourceObject);
    }

    @Override
    public T from(U object) {
        return transformer.from(object);
    }

    @Override
    public Class<T> getSourceType() {
        return transformer.getSourceType();
    }

    @Override
    public Class<U> getType() {
        return transformer.getType();
    }
}
