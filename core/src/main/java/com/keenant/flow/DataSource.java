package com.keenant.flow;

import com.keenant.flow.jdbc.QueryConfig;

/**
 * Abstraction of a {@link java.sql.Connection}, but it could be more broad. It could represent
 * a pool of connections for example.
 */
public interface DataSource {
    Query prepareQuery(QueryConfig config);
}
