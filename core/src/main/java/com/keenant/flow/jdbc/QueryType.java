package com.keenant.flow.jdbc;

import java.sql.ResultSet;

/**
 * Some drivers support moving back and forth in a result set.
 */
public enum QueryType {
    FORWARD_ONLY(ResultSet.TYPE_FORWARD_ONLY),
    SCROLL_INSENSITIVE(ResultSet.TYPE_SCROLL_INSENSITIVE),
    SCROLL_SENSITIVE(ResultSet.TYPE_SCROLL_SENSITIVE);

    private int value;

    QueryType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
