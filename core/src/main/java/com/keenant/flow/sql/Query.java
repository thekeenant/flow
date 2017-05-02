package com.keenant.flow.sql;

public interface Query extends AutoCloseable {
    Result execute();

    @Override
    void close();
}
