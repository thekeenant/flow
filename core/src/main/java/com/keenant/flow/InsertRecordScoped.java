package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;

public interface InsertRecordScoped extends InsertRecord {
    QueryPart build();

    void execute() throws DatabaseException;

    @Override
    InsertRecordScoped cpy();

    @Override
    InsertRecordScoped table(Exp table);

    @Override
    InsertRecordScoped with(String field, Exp value);

    @Override
    InsertRecordScoped with(String field, Object value);

    @Override
    InsertRecordScoped newRecord();
}
