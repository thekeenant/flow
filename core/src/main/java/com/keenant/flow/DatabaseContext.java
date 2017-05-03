package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;
import com.keenant.flow.jdbc.QueryConfig;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents a database which can be accessed and manipulated. Any number of connections may be
 * made to the underlying database.
 */
public interface DatabaseContext extends AutoCloseable {
    Query prepareQuery(String sql, List<Object> params, QueryConfig config);

    default Query prepareQuery(QueryPart part, QueryConfig config) {
        return prepareQuery(part.getSql(), part.getParams(), config);
    }
    
    default Query prepareQuery(String sql, QueryConfig config) {
      return prepareQuery(sql, Collections.emptyList(), config);
    }

    EagerCursor fetch(String sql, List<Object> params);

    default EagerCursor fetch(QueryPart part) {
        return fetch(part.getSql(), part.getParams());
    }

    default EagerCursor fetch(String sql, Object... params) {
        return fetch(sql, Arrays.asList(params));
    }

    Cursor fetchLazy(String sql, List<Object> params);

    default Cursor fetchLazy(QueryPart part) {
        return fetchLazy(part.getSql(), part.getParams());
    }

    default Cursor fetchLazy(String sql, Object... params) {
        return fetchLazy(sql, Arrays.asList(params));
    }

    SelectScoped selectFrom(Exp table);

    InsertScoped insertInto(Exp table);

    @Override
    void close() throws DatabaseException;
}
