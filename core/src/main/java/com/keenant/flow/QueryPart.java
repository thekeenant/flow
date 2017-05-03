package com.keenant.flow;

import java.util.List;

/**
 * Data class that holds a parameterized SQL string.
 *
 * It may be a small component of a full query, or the full query itself.
 */
public interface QueryPart {
    String getSql();

    List<Object> getParams();
}
