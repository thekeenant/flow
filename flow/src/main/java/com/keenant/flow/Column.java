package com.keenant.flow;

import com.keenant.flow.exp.Field;

public class Column<T> extends Field {
    private final Field table;
    private final String name;
    private final Class<T> type;

    public Column(Field table, String name, Class<T> type) {
        super(table, name);
        this.table = table;
        this.name = name;
        this.type = type;
    }

    public Field getTable() {
        return table;
    }

    public String getName() {
        return name;
    }

    public Class<T> getType() {
        return type;
    }
}
