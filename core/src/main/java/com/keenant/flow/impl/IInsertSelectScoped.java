package com.keenant.flow.impl;

import com.keenant.flow.*;

public class IInsertSelectScoped implements InsertSelectScoped {
    private final InsertSelect insert;
    private final DatabaseContext database;
    private final SQLDialect dialect;

    private IInsertSelectScoped(InsertSelect insert, DatabaseContext database, SQLDialect dialect) {
        this.insert = insert;
        this.database = database;
        this.dialect = dialect;
    }

    public IInsertSelectScoped(Exp exp, Select select, DatabaseContext database, SQLDialect dialect) {
        this(new IInsertSelect(exp, select), database, dialect);
    }

    @Override
    public InsertSelectScoped cpy() {
        return new IInsertSelectScoped(insert.cpy(), database, dialect);
    }

    @Override
    public InsertSelectScoped table(Exp table) {
        insert.table(table);
        return this;
    }

    @Override
    public QueryPart build() {
        return insert.build(dialect);
    }

    @Override
    public void execute() {
        insert.execute(database, dialect);
    }

    @Override
    public QueryPart build(SQLDialect dialect) {
        return insert.build(dialect);
    }

    @Override
    public void execute(DatabaseContext database, SQLDialect dialect) {
        insert.execute(database, dialect);
    }
}
