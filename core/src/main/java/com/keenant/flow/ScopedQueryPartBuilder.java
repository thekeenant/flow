package com.keenant.flow;

/**
 *
 *
 * @see QueryPartBuilder
 */
public interface ScopedQueryPartBuilder extends QueryPartBuilder {
    /**
     * @see Select#build(SQLDialect)
     */
    QueryPart build();

    /**
     * @return the database associated with this query
     */
    DatabaseContext getDatabase();

    /**
     * @return the dialect associated with this query
     */
    SQLDialect getDialect();
}
