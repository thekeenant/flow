package com.keenant.flow.jdbc;

public class QueryConfig {
    private final QueryMode mode;
    private final QueryType type;
    private final QueryConcurrency concurrency;
    private final Integer timeout;

    public QueryConfig(QueryMode mode, QueryType type, QueryConcurrency concurrency, Integer timeout) {
        if (mode == null)
            throw new IllegalArgumentException("Query mode must not be null");

        this.mode = mode;
        this.type = type == null ? QueryType.FORWARD_ONLY : type;
        this.concurrency = concurrency == null ? QueryConcurrency.READ_ONLY : concurrency;
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

    public static Builder builder(QueryMode mode) {
        return new Builder().mode(mode);
    }

    public static final class Builder {
        private QueryMode mode;
        private QueryType type;
        private QueryConcurrency concurrency;
        private Integer timeout;

        private Builder() {
        }

        public Builder mode(QueryMode mode) {
            this.mode = mode;
            return this;
        }

        public Builder type(QueryType type) {
            this.type = type;
            return this;
        }

        public Builder concurrency(QueryConcurrency concurrency) {
            this.concurrency = concurrency;
            return this;
        }

        public Builder timeout(Integer timeout) {
            this.timeout = timeout;
            return this;
        }

        public QueryConfig build() {
            QueryConfig queryConfig = new QueryConfig(mode, type, concurrency, timeout);
            return queryConfig;
        }
    }
}
