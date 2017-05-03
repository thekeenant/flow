package com.keenant.flow.impl;

import com.keenant.flow.*;

public class IInsertScoped implements InsertScoped {
    private final Exp table;
    private final DatabaseContext database;
    private final SQLDialect dialect;

    public IInsertScoped(Exp table, DatabaseContext database, SQLDialect dialect) {
        this.table = table;
        this.database = database;
        this.dialect = dialect;
    }

    @Override
    public InsertSelectScoped select(Select select) {
        return new IInsertSelectScoped(table, select, database, dialect);
    }

    @Override
    public InsertRecordScoped newRecord() {
        return new IInsertRecordScoped(table, database, dialect);
    }
}
