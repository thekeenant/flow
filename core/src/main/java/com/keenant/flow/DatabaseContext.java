package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;
import com.keenant.flow.jdbc.QueryConfig;

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

    SelectScoped selectFrom(Exp table);

    InsertScoped insertInto(Exp table);

    @Override
    void close() throws DatabaseException;
}
