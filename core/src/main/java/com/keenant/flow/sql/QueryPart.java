package com.keenant.flow.sql;

import java.util.Collections;
import java.util.List;

public interface QueryPart {
    QueryPart EMPTY = new QueryPart() {
        @Override
        public String getSql() {
            return "";
        }

        @Override
        public List<Object> getParams() {
            return Collections.emptyList();
        }
    };

    String getSql();

    List<Object> getParams();
}
