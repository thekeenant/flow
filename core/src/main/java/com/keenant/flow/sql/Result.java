package com.keenant.flow.sql;

public interface Result extends AutoCloseable {
    /**
     * Create a cursor to traverse records that were fetched.
     * @return the new cursor.
     */
    Cursor lazyCursor();

    /**
     * Create a cursor to traverse records that were fetched all at once.
     * It is eager, so it can move to any record (unless it is not supported).
     * @return the new cursor
     */
    EagerCursor eagerCursor();

    /**
     * Create a cursor to traverse records that were fetched all at once.
     * This is an alternative to {@link #eagerCursor()}, but it should, in addition,
     * support databases that do not have eager fetching.
     *
     * The cost to this is both in time and memory.
     *
     * @return the new cursor
     */
    EagerCursor safeEagerCursor();

    /**
     * Create a cursor to traverse returned generated fields.
     * @return the new cursor.
     * @throws IllegalStateException if there is no generated results
     */
    Cursor generatedCursor() throws IllegalStateException;

    /**
     * CLose the underlying statement and result.
     *
     * No exceptions thrown.
     */
    @Override
    void close();
}
