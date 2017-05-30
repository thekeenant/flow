package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;
import com.keenant.flow.exp.ParamExp;

public class InsertScoped implements QueryPartBuilder {
    private final Insert insert;
    private final DatabaseContext database;
    private final SQLDialect dialect;

    private InsertScoped(Insert insert, DatabaseContext database, SQLDialect dialect) {
        this.insert = insert;
        this.database = database;
        this.dialect = dialect;
    }

    public InsertScoped(Exp exp, DatabaseContext database, SQLDialect dialect) {
        this(new Insert(exp), database, dialect);
    }

    public InsertScoped cpy() {
        return new InsertScoped(insert.cpy(), database, dialect);
    }

    public InsertScoped table(Exp table) {
        insert.table(table);
        return this;
    }

    public InsertScoped with(String field, Exp value) {
        insert.with(field, value);
        return this;
    }

    public InsertScoped with(String field, Object value) {
        return with(field, new ParamExp(value));
    }

    public void execute() throws DatabaseException {
        insert.execute(database, dialect);
    }

    public InsertScoped newRecord() {
        insert.newRecord();
        return this;
    }

    public QueryPart build() {
        return insert.build(dialect);
    }

    public DatabaseContext getDatabase() {
        return database;
    }

    public SQLDialect getDialect() {
        return dialect;
    }

    @Override
    public QueryPart build(SQLDialect dialect) {
        return insert.build(dialect);
    }

    public void execute(DatabaseContext database, SQLDialect dialect) {
        insert.execute(database, dialect);
    }
}
