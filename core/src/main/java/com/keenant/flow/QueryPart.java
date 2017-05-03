package com.keenant.flow;

import java.util.Arrays;
import java.util.List;

/**
 * Data class that holds a parameterized SQL string.
 *
 * It may be a small component of a full query, or the full query itself.
 */
public interface QueryPart {
    String getSql();

    List<Object> getParams();

    QueryPart join(String sql, List<Object> params);

    default QueryPart join(String sql, Object... params) {
        return join(sql, Arrays.asList(params));
    }

    default QueryPart join(QueryPart other) {
        return join(other.getSql(), other.getParams());
    }
}
