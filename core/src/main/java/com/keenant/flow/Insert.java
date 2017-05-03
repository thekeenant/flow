package com.keenant.flow;

public interface Insert {
    /**
     * Create a copy of this object.
     * @return the new insert
     */
    Insert cpy();

    Insert with(String field, Exp value);

    Insert with(String field, Object value);

    Insert newRecord();

    QueryPart build(SQLDialect dialect);

    void execute(DatabaseContext database, SQLDialect dialect);
}
