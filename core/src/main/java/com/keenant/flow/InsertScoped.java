package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;

public interface InsertScoped extends ScopedQueryPartBuilder, Insert {
    /**
     * @see Insert#build(SQLDialect)
     */
    void execute() throws DatabaseException;

    @Override
    InsertScoped cpy();

    @Override
    InsertScoped table(Exp table);

    @Override
    InsertScoped with(String field, Exp value);

    @Override
    InsertScoped with(String field, Object value);

    @Override
    InsertScoped newRecord();
}
