package com.keenant.flow.sql;

import com.keenant.flow.exception.DatasourceException;

public interface Select {
    /**
     * Copy all features of this select into a new object.
     * @return the new select
     */
    Select cpy();

    /**
     * Set the where condition for this query.
     * @param filter the filtering condition
     * @return this object
     */
    Select where(Filter filter);

    /**
     * Build the complete query.
     * @param dialect the dialect to use
     * @return the query
     */
    QueryPart build(SQLDialect dialect);

    /**
     * Execute the query and put all records into memory. On large queries this can be very costly.
     * @param database the database
     * @param dialect the dialect to use
     * @return the cursor
     */
    EagerCursor fetch(SQLDatabase database, SQLDialect dialect);

    /**
     * Execute the query lazily, fetching each record one by one.
     * @param database the database
     * @param dialect the dialect to use
     * @return the lazy cursor
     */
    Cursor fetchLazy(SQLDatabase database, SQLDialect dialect) throws DatasourceException;
}
