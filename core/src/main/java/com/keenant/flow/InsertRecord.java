package com.keenant.flow;

public interface InsertRecord {
    InsertRecord cpy();

    InsertRecord table(Exp table);

    InsertRecord with(String field, Exp value);

    InsertRecord with(String field, Object value);

    InsertRecord newRecord();

    QueryPart build(SQLDialect dialect);

    void execute(DatabaseContext database, SQLDialect dialect);
}
