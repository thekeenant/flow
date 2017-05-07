package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;
import com.keenant.flow.jdbc.QueryConfig;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a database which can be accessed and manipulated. Any number of connections may be
 * made to the underlying database.
 */
public interface DatabaseContext extends AutoCloseable {
    /**
     * Prepare a query for execution.
     * @param sql the sql string
     * @param params the sql parameters (replaces ?'s in the sql string)
     * @param config the query configuration
     * @return the prepared query
     */
    Query prepareQuery(String sql, List<Object> params, QueryConfig config);

    /**
     * @see #prepareQuery(String, List, QueryConfig)
     */
    default Query prepareQuery(QueryPart part, QueryConfig config) {
        return prepareQuery(part.getSql(), part.getParams(), config);
    }

    /**
     * Perform a query which returns a cursor (i.e., select query)
     * @param sql the select sql query
     * @param params sql parameters
     * @return an eager cursor
     */
    EagerCursor fetch(String sql, List<Object> params);

    /**
     * @see #fetch(String, List)
     */
    default EagerCursor fetch(QueryPart part) {
        return fetch(part.getSql(), part.getParams());
    }

    /**
     * @see #fetch(String, List)
     */
    default EagerCursor fetch(String sql, Object... params) {
        return fetch(sql, Arrays.asList(params));
    }

    /**
     * Perform a query which returns a lazy cursor (streaming each record, rather than
     * JDBC loading them all into memory).
     * @param sql the select sql query
     * @param params sql parameters
     * @return a lazy cursor
     */
    Cursor fetchLazy(String sql, List<Object> params);

    /**
     * @see #fetchLazy(String, List)
     */
    default Cursor fetchLazy(QueryPart part) {
        return fetchLazy(part.getSql(), part.getParams());
    }

    /**
     * @see #fetchLazy(String, List)
     */
    default Cursor fetchLazy(String sql, Object... params) {
        return fetchLazy(sql, Arrays.asList(params));
    }

    /**
     * Construct the start of a select query.
     * @param table the table to select from
     * @return the select query
     */
    SelectScoped selectFrom(Exp table);

    /**
     * Construct the start of an insert query.
     * @param table the table to insert into
     * @return the insert query
     */
    InsertScoped insertInto(Exp table);

    @Override
    void close() throws DatabaseException;
}
