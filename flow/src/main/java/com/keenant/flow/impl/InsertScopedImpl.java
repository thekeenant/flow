package com.keenant.flow.impl;

import com.keenant.flow.*;
import com.keenant.flow.exception.DatabaseException;
import com.keenant.flow.impl.exp.ParamExp;

public class InsertScopedImpl implements InsertScoped {
    private final Insert insert;
    private final DatabaseContext database;
    private final SQLDialect dialect;

    private InsertScopedImpl(Insert insert, DatabaseContext database, SQLDialect dialect) {
        this.insert = insert;
        this.database = database;
        this.dialect = dialect;
    }

    public InsertScopedImpl(Exp exp, DatabaseContext database, SQLDialect dialect) {
        this(new InsertImpl(exp), database, dialect);
    }

    @Override
    public InsertScoped cpy() {
        return new InsertScopedImpl(insert.cpy(), database, dialect);
    }

    @Override
    public InsertScoped table(Exp table) {
        insert.table(table);
        return this;
    }

    @Override
    public InsertScoped with(String field, Exp value) {
        insert.with(field, value);
        return this;
    }

    @Override
    public InsertScoped with(String field, Object value) {
        return with(field, new ParamExp(value));
    }

    @Override
    public void execute() throws DatabaseException {
        insert.execute(database, dialect);
    }

    @Override
    public InsertScoped newRecord() {
        insert.newRecord();
        return this;
    }

    @Override
    public QueryPart build() {
        return insert.build(dialect);
    }

    @Override
    public DatabaseContext getDatabase() {
        return database;
    }

    @Override
    public SQLDialect getDialect() {
        return dialect;
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
