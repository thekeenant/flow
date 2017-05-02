package com.keenant.flow.sql;

public interface Select {
    Select where(Filter filter);

    QueryPart build(SQLDialect dialect);

    EagerCursor fetch(SQLDatabase database, SQLDialect dialect);

    Cursor fetchLazy(SQLDatabase database, SQLDialect dialect);
}
