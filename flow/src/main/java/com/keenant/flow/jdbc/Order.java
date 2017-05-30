package com.keenant.flow.jdbc;

public enum Order {
    ASC("ASC"),

    DESC("DESC");

    private final String sql;

    Order(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}
