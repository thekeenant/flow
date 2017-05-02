package com.keenant.flow.sql.jdbc;

import java.sql.ResultSet;

public enum QueryConcurrency {
    READ_ONLY(ResultSet.CONCUR_READ_ONLY),
    UPDATABLE(ResultSet.CONCUR_UPDATABLE);

    private int value;

    QueryConcurrency(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
