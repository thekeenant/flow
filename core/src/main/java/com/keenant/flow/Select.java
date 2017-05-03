package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;

import java.util.Collection;

public interface Select {
    /**
     * Create a copy of this object.
     * @return the new select
     */
    Select cpy();

    Select table(Exp table);

    Select fields(Collection<Exp> fields);

    Select fields(Exp... fields);

    /**
     * Set the where condition for this query.
     * @param filter the filtering condition
     * @return this object
     */
    Select where(Filter filter);

    Select order(Exp order);

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
     * @throws DatabaseException if the execution fails
     */
    EagerCursor fetch(SQLDatabase database, SQLDialect dialect) throws DatabaseException;

    /**
     * Execute the query lazily, fetching each record one by one.
     * @param database the database
     * @param dialect the dialect to use
     * @return the lazy cursor
     * @throws DatabaseException if the execution fails
     */
    Cursor fetchLazy(SQLDatabase database, SQLDialect dialect) throws DatabaseException;
}
