package com.keenant.flow.jdbc;

/**
 * There are two types of queries in JDBC, which have subtypes.
 */
public enum QueryType {
    /**
     * A select query to the database.
     */
    FETCH,

    /**
     * Any type of database update such as a delete, create, update.
     */
    UPDATE;
}
