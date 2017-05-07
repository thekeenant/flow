package com.keenant.flow;

public interface Insert {
    Insert cpy();

    Insert table(Exp table);

    Insert with(String field, Exp value);

    Insert with(String field, Object value);

    Insert newRecord();

    QueryPart build(SQLDialect dialect);

    void execute(DatabaseContext database, SQLDialect dialect);
}
