package com.keenant.flow;

import java.util.List;

/**
 * Data class that holds a parameterized SQL string.
 */
public interface QueryPart {
    String getSql();

    List<Object> getParams();
}
