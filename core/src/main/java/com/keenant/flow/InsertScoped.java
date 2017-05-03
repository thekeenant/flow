package com.keenant.flow;


/**
 * An insert query which is targeted for a specific database with a specific dialect.
 */
public interface InsertScoped extends Insert {
    @Override
    InsertSelectScoped select(Select select);

    @Override
    InsertRecordScoped newRecord();
}
