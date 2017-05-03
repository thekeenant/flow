package com.keenant.flow.impl;

import com.keenant.flow.*;
import com.keenant.flow.exception.DatabaseException;
import com.keenant.flow.impl.exp.ParamExp;

public class IInsertRecordScoped implements InsertRecordScoped {
    private final InsertRecord insert;
    private final DatabaseContext database;
    private final SQLDialect dialect;

    private IInsertRecordScoped(InsertRecord insert, DatabaseContext database, SQLDialect dialect) {
        this.insert = insert;
        this.database = database;
        this.dialect = dialect;
    }

    public IInsertRecordScoped(Exp exp, DatabaseContext database, SQLDialect dialect) {
        this(new IInsertRecord(exp), database, dialect);
    }

    @Override
    public InsertRecordScoped cpy() {
        return new IInsertRecordScoped(insert.cpy(), database, dialect);
    }

    @Override
    public InsertRecordScoped table(Exp table) {
        insert.table(table);
        return this;
    }

    @Override
    public InsertRecordScoped with(String field, Exp value) {
        insert.with(field, value);
        return this;
    }

    @Override
    public InsertRecordScoped with(String field, Object value) {
        return with(field, new ParamExp(value));
    }

    @Override
    public void execute() throws DatabaseException {
        insert.execute(database, dialect);
    }

    @Override
    public InsertRecordScoped newRecord() {
        insert.newRecord();
        return this;
    }

    @Override
    public QueryPart build() {
        return insert.build(dialect);
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
