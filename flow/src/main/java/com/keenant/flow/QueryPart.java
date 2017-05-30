package com.keenant.flow;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Data class that holds a partial or complete parameterized SQL string.
 */
public interface QueryPart {
    String getSql();

    List<Object> getParams();

    QueryPart join(String sql, Collection<Object> params);

    default QueryPart join(String sql, Object... params) {
        return join(sql, Arrays.asList(params));
    }

    default QueryPart join(QueryPart other) {
        return join(other.getSql(), other.getParams());
    }
}
