package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;
import com.keenant.flow.jdbc.QueryConfig;

import java.util.List;

/**
 * Represents a database connection that can be opened and closed.
 */
public interface SQLDatabase extends AutoCloseable {
    SQLDatabase open() throws DatabaseException;

    void close() throws DatabaseException;

    Query prepareQuery(String sql, List<Object> params, QueryConfig config);

    default Query prepareQuery(QueryPart part, QueryConfig config) {
        return prepareQuery(part.getSql(), part.getParams(), config);
    }

    SelectScoped selectFrom(Exp table);

    InsertScoped insertInto(Exp table);
}
