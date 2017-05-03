package com.keenant.flow.impl;

import com.keenant.flow.*;

public class IInsert implements Insert {
    private final Exp table;

    public IInsert(Exp table) {
        this.table = table;
    }

    @Override
    public InsertSelect select(Select select) {
        return new IInsertSelect(table, select);
    }

    @Override
    public InsertRecord newRecord() {
        return new IInsertRecord(table);
    }
}
