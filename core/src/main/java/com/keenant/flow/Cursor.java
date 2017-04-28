package com.keenant.flow;

import java.util.NoSuchElementException;

/**
 * Abstraction of a {@link java.sql.ResultSet}.
 */
public interface Cursor {
    /**
     * Moves the cursor to the next record.
     * @return true if the cursor points to a record after moving to the next.
     */
    boolean moveNext();

    /**
     * Moves the cursor to the next record.
     * @return the cursor instance itself.
     * @throws NoSuchElementException
     */
    Cursor next() throws NoSuchElementException;
}
