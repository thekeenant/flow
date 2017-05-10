package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;
import com.keenant.flow.jdbc.FetchConfig;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Represents a database which can be accessed and manipulated. Any number of connections may be
 * made to the underlying database.
 */
public interface DatabaseContext extends AutoCloseable {
    /**
     * Prepare an update query for execution.
     * @param sql the sql string
     * @param params the sql parameters (replaces ?'s in the sql string)
     * @return the prepared query
     */
    Query prepareUpdate(String sql, Collection<?> params);

    /**
     * @see #prepareUpdate(String, Collection)
     */
    default Query prepareUpdate(QueryPart part) {
        return prepareUpdate(part.getSql(), part.getParams());
    }

    /**
     * @see #prepareUpdate(String, Collection)
     */
    default Query prepareUpdate(String sql, Object... params) {
        return prepareUpdate(sql, Arrays.asList(params));
    }

    /**
     * Prepare a fetch query for execution.
     * @param config the query configuration
     * @param sql the sql string
     * @param params the sql parameters (replaces ?'s in the sql string)
     * @return the prepared query
     */
    Query prepareFetch(FetchConfig config, String sql, Collection<?> params);

    /**
     * @see #prepareFetch(FetchConfig, String, Collection)
     */
    default Query prepareFetch(FetchConfig config, QueryPart part) {
        return prepareFetch(config, part.getSql(), part.getParams());
    }

    /**
     * @see #prepareFetch(FetchConfig, String, Collection)
     */
    default Query prepareFetch(FetchConfig config, String sql, Object... params) {
        return prepareFetch(config, sql, Arrays.asList(params));
    }

    /**
     * Perform a query which returns a cursor (i.e., select query)
     * @param sql the select sql query
     * @param params sql parameters
     * @return an eager cursor
     */
    EagerCursor fetch(String sql, Collection<?> params);

    /**
     * @see #fetch(String, Collection)
     */
    default EagerCursor fetch(QueryPart part) {
        return fetch(part.getSql(), part.getParams());
    }

    /**
     * @see #fetch(String, Collection)
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
    Cursor fetchLazy(String sql, Collection<?> params);

    /**
     * @see #fetchLazy(String, Collection)
     */
    default Cursor fetchLazy(QueryPart part) {
        return fetchLazy(part.getSql(), part.getParams());
    }

    /**
     * @see #fetchLazy(String, Collection)
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
