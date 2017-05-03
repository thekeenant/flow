package com.keenant.flow;

public interface Insert {
    InsertSelect select(Select select);

    InsertRecord newRecord();
}
