package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;

public interface InsertScoped extends Insert {
    QueryPart build();

    void execute() throws DatabaseException;

    // Override Insert

    @Override
    InsertScoped cpy();

    @Override
    InsertScoped with(String field, Exp value);

    @Override
    InsertScoped with(String field, Object value);

    @Override
    InsertScoped newRecord();
}
