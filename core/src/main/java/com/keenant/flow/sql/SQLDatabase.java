package com.keenant.flow.sql;

import com.keenant.flow.Datasource;
import com.keenant.flow.sql.jdbc.QueryConfig;

import java.util.List;

/**
 * Abstraction of a {@link java.sql.Connection}, but it could be more broad. It could represent
 * a pool of connections for example.
 */
public interface SQLDatabase extends Datasource {
    Query prepareQuery(String sql, List<Object> params, QueryConfig config);

    default Query prepareQuery(QueryPart part, QueryConfig config) {
        return prepareQuery(part.getSql(), part.getParams(), config);
    }
}
