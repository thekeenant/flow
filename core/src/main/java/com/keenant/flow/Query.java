package com.keenant.flow;

public interface Query extends AutoCloseable {
    Result execute();

    @Override
    void close();
}
