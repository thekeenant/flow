package com.keenant.flow.jdbc;

public class QueryConfig {
    private final QueryMode mode;
    private final QueryType type;
    private final QueryConcurrency concurrency;
    private final Integer timeout;

    public QueryConfig(QueryMode mode, QueryType type, QueryConcurrency concurrency, Integer timeout) {
        this.mode = mode;
        this.type = type;
        this.concurrency = concurrency;
        this.timeout = timeout;
    }

    public QueryMode getMode() {
        return mode;
    }

    public QueryType getType() {
        return type;
    }

    public QueryConcurrency getConcurrency() {
        return concurrency;
    }

    public Integer getTimeout() {
        return timeout;
    }
}
