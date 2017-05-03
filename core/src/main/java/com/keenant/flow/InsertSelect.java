package com.keenant.flow;

public interface InsertSelect {
    InsertSelect cpy();

    InsertSelect table(Exp table);

    QueryPart build(SQLDialect dialect);

    void execute(DatabaseContext database, SQLDialect dialect);
}
