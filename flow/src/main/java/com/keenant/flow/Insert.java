package com.keenant.flow;

public interface Insert extends QueryPartBuilder {
    Insert cpy();

    Insert table(Exp table);

    Insert with(String field, Exp value);

    Insert with(String field, Object value);

    Insert newRecord();

    void execute(DatabaseContext database, SQLDialect dialect);
}
