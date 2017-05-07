package com.keenant.flow;

import com.keenant.flow.exception.DatabaseException;

import java.util.Arrays;
import java.util.Collection;

/**
 * A select query.
 */
public interface Select {
    /**
     * Create a copy of this object.
     * @return the new select
     */
    Select cpy();

    /**
     * Set if this should be SELECT DISTINCT.
     * @param distinct true for DISTINCT
     * @return this
     */
    Select distinct(boolean distinct);

    /**
     * Set the table to select from.
     * @param table the table
     * @return this
     */
    Select table(Exp table);

    /**
     * Set which fields to retrieve
     * @param fields the fields
     * @return this
     */
    Select fields(Collection<Exp> fields);

    /**
     * See {@link #fields(Collection)}.
     */
    default Select fields(Exp... fields) {
        return fields(Arrays.asList(fields));
    }

    /**
     * Set the where condition for this query.
     * @param filter the filtering condition expression
     * @return this
     */
    Select where(Filter filter);

    /**
     * Set how to order the select.
     * @param order the order expression
     * @return this
     */
    Select order(Exp order);

    /**
     * Build the complete query.
     * @param dialect the dialect to use
     * @return the parameterized query
     */
    QueryPart build(SQLDialect dialect);

    /**
     * Execute the query and put all records into memory. On large queries this can be very costly.
     * @param database the database
     * @param dialect the dialect to use
     * @return the cursor
     * @throws DatabaseException if the execution fails
     */
    EagerCursor fetch(DatabaseContext database, SQLDialect dialect) throws DatabaseException;

    /**
     * Execute the query lazily, fetching each record one by one.
     * @param database the database
     * @param dialect the dialect to use
     * @return the lazy cursor
     * @throws DatabaseException if the execution fails
     */
    Cursor fetchLazy(DatabaseContext database, SQLDialect dialect) throws DatabaseException;
}
