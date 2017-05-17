package com.keenant.flow;

public interface Query extends AutoCloseable {
    /**
     * Execute this query on the database.
     * @return the result of execution
     */
    Result execute();

    @Override
    void close();
}
