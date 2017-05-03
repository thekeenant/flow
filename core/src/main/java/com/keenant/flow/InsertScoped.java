package com.keenant.flow;

public interface InsertScoped extends Insert {
    @Override
    InsertSelectScoped select(Select select);

    @Override
    InsertRecordScoped newRecord();
}
