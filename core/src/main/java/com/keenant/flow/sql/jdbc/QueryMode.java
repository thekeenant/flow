package com.keenant.flow.sql.jdbc;

public enum QueryMode {
    /**
     * Any type of database update such as a delete, create, update.
     */
    UPDATE,

    /**
     * A select query to the database.
     */
    FETCH
}
