package com.keenant.flow.jdbc;

/**
 * Configuration for creating and executing an SQL query. Use the {@link #builder(QueryType)} method to
 * build a new config.
 */
public final class FetchConfig {
    public static final FetchConfig DEFAULT = FetchConfig.builder().build();

    private final QueryScroll type;
    private final QueryConcurrency concurrency;
    private final Integer timeout;

    private FetchConfig(QueryType mode, QueryScroll type, QueryConcurrency concurrency, Integer timeout) {
        if (mode == null)
            throw new IllegalArgumentException("Query mode must not be null");

        this.type = type == null ? QueryScroll.FORWARD_ONLY : type;
        this.concurrency = concurrency == null ? QueryConcurrency.READ_ONLY : concurrency;
        this.timeout = timeout;
    }

    public QueryScroll getType() {
        return type;
    }

    public QueryConcurrency getConcurrency() {
        return concurrency;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private QueryScroll type;
        private QueryConcurrency concurrency;
        private Integer timeout;

        private Builder() {
        }

        public Builder type(QueryScroll type) {
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

        public FetchConfig build() {
            return new FetchConfig(QueryType.FETCH, type, concurrency, timeout);
        }
    }
}
