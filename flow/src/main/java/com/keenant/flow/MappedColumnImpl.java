package com.keenant.flow;

import com.keenant.flow.exp.FieldExp;
import com.keenant.flow.util.Transformer;

public class MappedColumnImpl<T, U> extends FieldExp implements MappedColumn<T, U> {
    private final FieldExp table;
    private final String name;
    private final Transformer<T, U> transformer;

    public MappedColumnImpl(FieldExp table, String name, Transformer<T, U> transformer) {
        super(table, name);
        this.table = table;
        this.name = name;
        this.transformer = transformer;
    }

    @Override
    public FieldExp getTable() {
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
