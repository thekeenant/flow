package com.keenant.flow.impl;

import com.keenant.flow.Column;
import com.keenant.flow.impl.exp.Field;

public class ColumnImpl<T> extends Field implements Column<T> {
    private final Field table;
    private final String name;
    private final Class<T> type;

    public ColumnImpl(Field table, String name, Class<T> type) {
        super(table, name);
        this.table = table;
        this.name = name;
        this.type = type;
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
    public Class<T> getType() {
        return type;
    }
}
